package project;

import model.ProductType;
import service.Receipt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CashRegister {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Receipt rc = new Receipt();

        while (true) {
            try {
                System.out.print("\n--- Cash Register ---\n" +
                        "1. Add product\n" +
                        "2. Show receipt\n" +
                        "3. Checkout\n" +
                        "4. Save receipt to file\n" +
                        "5. Exit\n" +
                        "Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1 -> {
                        sc.nextLine();
                        System.out.println("Available products:");
                        for (ProductType type : ProductType.values()) {
                            System.out.printf("%d. %s - $%.2f\n", type.ordinal() + 1, type.getName(), type.getPrice());
                        }
                        System.out.print("Choose a product: ");
                        int productChoice = sc.nextInt();
                        if (productChoice < 1 || productChoice > ProductType.values().length) {
                            System.out.println("Invalid product choice.");
                            break;
                        }
                        ProductType selectedProduct = ProductType.values()[productChoice - 1];
                        System.out.print("Enter quantity: ");
                        int quantity = sc.nextInt();

                        rc.addProduct(selectedProduct, quantity);
                        System.out.println("Product added!");
                    }

                    case 2 -> rc.printReceipt();

                    case 3 -> {
                        rc.printReceipt();
                        System.out.println("Thank you for your purchase!");
                        rc.saveReceiptToFile();
                        return;
                    }

                    case 4 -> rc.saveReceiptToFile();

                    case 5 -> System.exit(0);

                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
        }
    }
}