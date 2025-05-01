package src;

public class Demo {
    public static void main(String[] args) {

        InventoryManagementSystem ims = InventoryManagementSystem.getInstance();

        // Add suppliers
        Supplier supplier1 = ims.addSupplier("Supplier 1", "1234567890");
        Supplier supplier2 = ims.addSupplier("Supplier 2", "1234567890");

        // Add materials
        Material material1 = supplier1.addMaterial("Room heater", "Heats room", 100);
        Material material2 = supplier1.addMaterial("Fan", "Cools room", 50);
        Material material3 = supplier2.addMaterial("Table", "Wooden table", 200);
        Material material4 = supplier2.addMaterial("Chair", "Wooden chair", 100);

        // Add consumers
        Consumer consumer1 = ims.addConsumer("Consumer 1", "Address 1", "qwerty", "1234567890");
        Consumer consumer2 = ims.addConsumer("Consumer 2", "Address 2", "qwerty1", "1234567891");

        // display materials
        ims.printInventory();

        // Create orders
        Order order1 = ims.createOrder(consumer1, material1, 2);
        Order order2 = ims.createOrder(consumer2, material3, 1);
        Order order3 = ims.createOrder(consumer1, material2, 3);
        Order order4 = ims.createOrder(consumer2, material4, 4);

        ims.orderDeliveredBySupplier(order1.getSupplier(), order1);
        ims.orderDeliveredBySupplier(order2.getSupplier(), order2);
        ims.orderDeliveredBySupplier(order3.getSupplier(), order3);
        ims.cancelOrder(consumer2, order4);
        ims.cancelOrder(consumer1, order1);

        ims.orderDeliveredToConsumer(order1);
        ims.orderDeliveredToConsumer(order2);
        ims.orderDeliveredToConsumer(order3);

        ims.settleSupplierBalance();

    }
}
