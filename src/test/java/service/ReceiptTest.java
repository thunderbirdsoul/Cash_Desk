package service;

import model.ProductType;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    // Adding product with quantity of 0
    @Test
    public void test_add_product_with_zero_quantity() {
        Receipt receipt = new Receipt();
        receipt.addProduct(ProductType.BANANA, 0);
        assertEquals(0.0, receipt.calculateTotal());
    }

    // Adding product with quantity of 1
    @Test
    public void test_add_single_product_with_quantity_one() {
        Receipt receipt = new Receipt();
        receipt.addProduct(ProductType.BANANA, 1);
        double expectedTotal = ProductType.BANANA.getPrice() * 1;
        assertEquals(expectedTotal, receipt.calculateTotal());
    }

    // Calculate total price for single product with no discount
    @Test
    public void test_calculate_total_single_product_no_discount() {
        Receipt receipt = new Receipt();
        receipt.addProduct(ProductType.BANANA, 2);

        double total = receipt.calculateTotal();

        assertEquals(3.0, total);
    }

    // Calculate total for empty product list
    @Test
    public void test_calculate_total_empty_list() {
        Receipt receipt = new Receipt();

        double total = receipt.calculateTotal();

        assertEquals(0.0, total);
    }

    // Sum multiple products with different quantities correctly
    @Test
    public void test_calculate_total_multiple_products_with_quantities() {
        Receipt receipt = new Receipt();
        receipt.addProduct(ProductType.BANANA, 2);
        receipt.addProduct(ProductType.BREAD, 1);
        receipt.addProduct(ProductType.CHOCOLATE, 5);

        double total = receipt.calculateTotal();

        assertEquals(19.2, total);
    }

    // Print receipt with single product showing correct product details and total
    @Test
    public void test_print_receipt_with_single_product() {
        Receipt receipt = new Receipt();
        receipt.addProduct(ProductType.CHOCOLATE, 2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        receipt.printReceipt();

        String expectedOutput = "\n--- Receipt ---\n" +
                "CHOCOLATE ($3.00 x 2) = $6.00\n" +
                "Total: $6.00\n";
        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }

    // Print receipt when products list is empty
    @Test
    public void test_print_receipt_with_empty_list() {
        Receipt receipt = new Receipt();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        receipt.printReceipt();

        String expectedOutput = "\n--- Receipt ---\n" +
                "No products added.\n";
        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }

    // Print receipt with products having very large quantities
    @Test
    public void test_print_receipt_with_large_quantities() {
        Receipt receipt = new Receipt();
        receipt.addProduct(ProductType.BREAD, 10000);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        receipt.printReceipt();

        String expectedOutput = "\n--- Receipt ---\n" +
                "BREAD ($1.20 x 10000) = $12000.00\n" +
                "Your discount: 2400.00\n" +
                "Total: $9600.00\n";
        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }
}