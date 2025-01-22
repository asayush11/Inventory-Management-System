package src;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private final String id;
    private final Material material;

    private final int quantity;
    private final Consumer consumer;
    private Supplier supplier;
    private final double totalPrice;
    private OrderStatus status;

    public Order(String id, Material material, int quantity, Consumer consumer){
        this.id = id;
        this.material = material;
        this.quantity = quantity;
        this.consumer = consumer;
        this.totalPrice = material.getPrice() * quantity;
        this.status = OrderStatus.REQUESTED;
    }

    public String getId(){
        return id;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void assignSupplier(Supplier supplier){
        this.supplier = supplier;
        status = OrderStatus.SUPPLIER_ASSIGNED;
    }

    public void updateStatus(OrderStatus status){
        this.status = status;
        System.out.println("Order status updated to: " + status);
    }


    public OrderStatus getStatus() {
        return status;
    }

    public Material getMaterial() {
        return material;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}
