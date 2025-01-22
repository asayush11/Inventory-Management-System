package src;

public class Material {
    private final String  name;
    private final String id;
    private final String description;
    private double price;

    public Material(String id, String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getId() {
        return id;
    }

}
