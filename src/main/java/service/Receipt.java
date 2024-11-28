package service;

import model.Product;
import model.ProductType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Product> products = new ArrayList<>();

    public void addProduct(ProductType productType, int quantity) {
        products.add(new Product(productType, quantity));
    }

    /**
     * Calculates the total price of all products in the cart,
     * applying a discount if the total price meets the discount criteria.
     *
     * @return the total price after applying any applicable discount
     */
    public double calculateTotal() {
        double totalSum = products.stream()
                .mapToDouble(Product::getTotalPrice)
                .sum();
        return applyDiscount(totalSum);
    }

    /**
     * Applies a discount to the total price if it meets the criteria.
     * A 20% discount is applied when the total price is greater than or equal to 500.
     *
     * @param totalSum the original total price of the products
     * @return the total price after applying the discount
     */
    private double applyDiscount(double totalSum) {
        // 20% discount if total >= 500
        if (totalSum >= 500) {
            double discount = totalSum * 0.2;
            System.out.printf("Your discount: $%.2f\n", discount);
            return totalSum - discount;
        }
        return totalSum;
    }

    /**
     * Prints the receipt to the console.
     * <p>
     * If no products have been added, it displays a message indicating that
     * the receipt is empty. Otherwise, it lists all the products and their
     * details, followed by the total price after applying any applicable discounts.
     */
    public void printReceipt() {
        if (products.isEmpty()) {
            System.out.println("\n--- Receipt ---");
            System.out.println("No products added.");
            return;
        }

        System.out.println("\n--- Receipt ---");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.printf("Total: $%.2f\n", calculateTotal());
    }

    /**
     * Saves the receipt to a file named "receipt.txt".
     * <p>
     * Each product is listed in the receipt file along with the total price
     * after applying any applicable discounts. If an error occurs during
     * file writing, it prints an error message and stack trace to the console.
     */
    public void saveReceiptToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.txt"))) {
            writer.write("--- Receipt ---\n");
            for (Product product : products) {
                writer.write(product + "\n");
            }
            writer.write(String.format("Total: $%.2f\n", calculateTotal()));
            System.out.println("Receipt saved to receipt.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt to file.");
            e.printStackTrace();
        }
    }
}