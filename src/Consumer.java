package src;

public class Consumer {
    private final String name;
    private final String address;
    private final String email;
    private final String phone;
    private final String id;

    public Consumer(String id, String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public Order requestOrder(InventoryManagementSystem ims, Material material, int quantity){
        return ims.createOrder(this, material, quantity);
    }

    public void cancelOrder(InventoryManagementSystem inventoryManagementSystem, Order order){
        inventoryManagementSystem.cancelOrder(order);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }
}
