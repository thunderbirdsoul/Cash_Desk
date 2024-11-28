package model;

public class Product {

    private ProductType type;
    private int quantity;

    public Product(ProductType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    /**
     * Calculates the total price of a product based on its price and quantity.
     *
     * @return the total price, calculated as the product of the unit price and quantity.
     */
    public double getTotalPrice() {
        return type.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s ($%.2f x %d) = $%.2f",
                type.name(), type.getPrice(), quantity, getTotalPrice());
    }
}