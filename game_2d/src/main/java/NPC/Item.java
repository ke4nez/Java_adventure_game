package NPC;

public class Item {
    private String name;
    private double mass;
    private int price;

    public Item(String name, double mass, int price) {
        this.name = name;
        this.mass = mass;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}