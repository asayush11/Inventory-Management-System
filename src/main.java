package src;

public class main {
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
        Order order1 = consumer1.requestOrder(ims, material1, 2);
        Order order2 = consumer2.requestOrder(ims, material3, 1);
        Order order3 = consumer1.requestOrder(ims, material2, 3);
        Order order4 = consumer2.requestOrder(ims, material4, 4);

        order1.getSupplier().markDelivered(ims, order1);
        order2.getSupplier().markDelivered(ims, order2);
        order3.getSupplier().markDelivered(ims, order3);
        consumer1.cancelOrder(ims, order3);
        consumer2.cancelOrder(ims, order4);

        ims.orderDeliveredToConsumer(order1);
        ims.orderDeliveredToConsumer(order2);
        ims.orderDeliveredToConsumer(order3);

        ims.settleSupplierBalance();

    }
}
