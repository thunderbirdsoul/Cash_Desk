package model;

public enum ProductType {
    APPLE("Apple", 2.0),
    BANANA("Banana", 1.5),
    CHOCOLATE("Chocolate", 3.0),
    BREAD("Bread", 1.2);

    private final String name;
    private final double price;

    ProductType(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}