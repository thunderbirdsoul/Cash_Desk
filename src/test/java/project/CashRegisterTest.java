package project;

import model.ProductType;
import org.junit.jupiter.api.Test;
import service.Receipt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class CashRegisterTest {
    // Successfully add a product with valid product choice and quantity
    @Test
    public void test_add_valid_product() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n2\n5\n".getBytes());
        System.setIn(in);

        Receipt rc = new Receipt();
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        assertEquals(1, choice);

        int productChoice = sc.nextInt();
        assertEquals(1, productChoice);

        int quantity = sc.nextInt();
        assertEquals(2, quantity);

        ProductType selectedProduct = ProductType.values()[productChoice - 1];
        rc.addProduct(selectedProduct, quantity);

        assertEquals(4.0, rc.calculateTotal());
    }

    // Display receipt with added products and total price
    @Test
    public void test_display_receipt() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Receipt rc = new Receipt();
        rc.addProduct(ProductType.APPLE, 2);
        rc.addProduct(ProductType.BANANA, 3);

        rc.printReceipt();

        String expectedOutput = "\n--- Receipt ---\n" +
                "APPLE ($2.00 x 2) = $4.00\n" +
                "BANANA ($1.50 x 3) = $4.50\n" +
                "Total: $8.50\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    // Handle invalid product choice
    @Test
    public void test_invalid_product_choice() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n10\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        int productChoice = sc.nextInt();

        assertTrue(productChoice < 1 || productChoice > ProductType.values().length);
    }

    // Handle invalid quantity input
    @Test
    public void test_invalid_quantity_input() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\nabc\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        int productChoice = sc.nextInt();

        assertThrows(InputMismatchException.class, () -> {
            sc.nextInt();
        });
    }

    // Successfully add a product with valid product choice and quantity
    @Test
    public void test_add_product_with_valid_choice_and_quantity() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n3\n5\n".getBytes());
        System.setIn(in);

        Receipt rc = new Receipt();
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        assertEquals(1, choice);

        int productChoice = sc.nextInt();
        assertEquals(2, productChoice);

        int quantity = sc.nextInt();
        assertEquals(3, quantity);

        ProductType selectedProduct = ProductType.values()[productChoice - 1];
        rc.addProduct(selectedProduct, quantity);

        assertEquals(4.5, rc.calculateTotal());
    }
}