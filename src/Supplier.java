package src;

import java.util.HashMap;
import java.util.Map;

public class Supplier {
    private final String name;
    private final String phoneNumber;
    public final String id;
    private final Map<String, Material> materials;
    private double balance;

    public Supplier(String id, String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.balance = 0;
        this.materials = new HashMap<>();
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance;
    }

    public void updateBalance(double balance){
        this.balance -= balance;
        System.out.println("Balance updated for supplier: " + name + " to: " + this.balance);
    }

    public void markDelivered(InventoryManagementSystem inventoryManagementSystem, Order order){
        inventoryManagementSystem.orderDeliveredBySupplier(order);
    }

    public Material addMaterial(String name, String description, double price){
        if(materials.containsKey(name)){
            System.out.println("Material already exists in supplier: " + name);
            return null;
        }
        String materialID = "M" + (materials.size() + 1);
        Material material = new Material(materialID, name, description, price);
        materials.put(name, material);
        System.out.println("Material " + material.getName() + " added to supplier: " + this.name);
        return material;
    }

    public void removeMaterial(Material material){
        if(!materials.containsKey(material.getName())){
            System.out.println("Material does not exist in supplier: " + name);
            return;
        }
        materials.remove(material.getName());
        System.out.println("Material " + material.getName() + " removed from supplier: " + name);
    }

    public Map<String, Material> getMaterials() {
        return materials;
    }

    public void updateMaterialPrice(Material material, double price){
        if(!materials.containsKey(material.getName())){
            System.out.println("Material does not exist in supplier: " + name);
            return;
        }
        material.setPrice(price);
        System.out.println("Price updated for material: " + material.getName() + " to: " + price + " by supplier: " + name);
    }
}
