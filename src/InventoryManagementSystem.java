package src;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManagementSystem {
    private static volatile InventoryManagementSystem instance;
    private final Map<String, Supplier> suppliers;
    private final Map<String, Consumer> consumers;
    private final Map<String, Order> orders;

    private InventoryManagementSystem(){
        suppliers = new HashMap<>();
        consumers = new HashMap<>();
        orders = new HashMap<>();
    }

    public static synchronized InventoryManagementSystem getInstance(){
        if(instance == null){
            synchronized (InventoryManagementSystem.class) {
                if (instance == null) {
                    instance = new InventoryManagementSystem();
                }
            }
        }
        return instance;
    }

    public Supplier addSupplier(String name, String phoneNumber){
        String supplierID = "S" + UUID.randomUUID().toString().replace("-", "");
        Supplier supplier = new Supplier(supplierID, name, phoneNumber);
        suppliers.put(supplierID, supplier);
        System.out.println("Supplier " + supplier.getName() + " added to inventory management system");
        return supplier;
    }

    public Consumer addConsumer(String name, String address, String email, String phone){
        String consumerID = "C" + UUID.randomUUID().toString().replace("-", "");
        Consumer consumer = new Consumer(consumerID, name, address, email, phone);
        consumers.put(consumerID, consumer);
        System.out.println("Consumer " + consumer.getName() + " added to inventory management system");
        return consumer;
    }

    public Order createOrder(Consumer consumer, Material material, int quantity){
        String orderID = "O" + UUID.randomUUID().toString().replace("-", "");
        Order order = new Order(orderID, material, quantity, consumer);
        orders.put(orderID, order);
        System.out.println("Order " + order.getId() + " created for consumer: " + consumer.getName());
        assignSupplierToOrder(order);
        return order;
    }

    public void cancelOrder(Consumer consumer, Order order){
        if(order == null || order.getConsumer().getName().compareTo(consumer.getName()) != 0 || ( order.getStatus() != OrderStatus.SUPPLIER_ASSIGNED && order.getStatus() != OrderStatus.REQUESTED )){
            System.out.println("Order cannot be cancelled");
            return;
        }
        order.updateStatus(OrderStatus.CANCELLED);
        System.out.println("Order " + order.getId() + " cancelled");
    }

    private void assignSupplierToOrder(Order order){
        if(order == null || order.getStatus() != OrderStatus.REQUESTED){
            System.out.println("Order cannot be assigned to supplier");
            return;
        }
        Material material = order.getMaterial();
        if(material == null){
            System.out.println("Material not found for order: " + order.getId());
            return;
        }
        suppliers.values().stream()
                .filter(supplier -> supplier.getMaterials().containsKey(material.getName()))
                .findFirst()
                .ifPresentOrElse(supplier -> {
                    order.assignSupplier(supplier);
                    order.updateStatus(OrderStatus.SUPPLIER_ASSIGNED);
                    System.out.println("Supplier " + supplier.getName() + " assigned to order: " + order.getId());
                }, () -> {
                    System.out.println("No supplier found for order: " + order.getId());
                    order.updateStatus(OrderStatus.CANCELLED);
                });

    }

    public void orderDeliveredBySupplier(Supplier supplier, Order ord){
        Order order = orders.get(ord.getId());
        if(order == null || order.getStatus() != OrderStatus.SUPPLIER_ASSIGNED || order.getSupplier().getName().compareTo(supplier.getName()) != 0){
            System.out.println("Order cannot be marked delivered by supplier");
            return;
        }
        order.updateStatus(OrderStatus.SUPPLIER_DELIVERED);
        order.getSupplier().updateBalance(order.getTotalPrice());
        System.out.println("Order " + order.getId() + " delivered by supplier: " + order.getSupplier().getName());
    }

    public void orderDeliveredToConsumer(Order ord){
        Order order = orders.get(ord.getId());
        if(order == null || order.getStatus() != OrderStatus.SUPPLIER_DELIVERED){
            System.out.println("Order cannot be delivered to consumer");
            return ;
        }
        order.updateStatus(OrderStatus.COMPLETED);
        System.out.println("Order " + order.getId() + " delivered to consumer: " + order.getConsumer().getName());
    }

    public void settleSupplierBalance(){
        suppliers.values().parallelStream()
                .filter(supplier -> supplier.getBalance() < 0)
                .forEach(supplier -> {
                    System.out.println("Balance settled for supplier: " + supplier.getName());
                    supplier.updateBalance(supplier.getBalance());
                });
    }

    public void printInventory(){
        System.out.println("Inventory Management System");
        suppliers.values().forEach(supplier -> {
            System.out.println("Supplier: " + supplier.getName());
            supplier.getMaterials().values().forEach(material -> {
                System.out.println("Material: " + material.getName() + " Price: " + material.getPrice());
            });
        });
    }
}
