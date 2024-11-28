package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    // Create Product with valid ProductType and positive quantity
    @Test
    public void test_create_product_with_valid_type_and_quantity() {
        Product product = new Product(ProductType.APPLE, 3);

        assertEquals(6.0, product.getTotalPrice(), 0.01);
        assertEquals("APPLE ($2.00 x 3) = $6.00", product.toString());
    }

    // Create Product with zero quantity and verify total price and string representation
    @Test
    public void test_create_product_with_zero_quantity() {
        Product product = new Product(ProductType.BANANA, 0);

        assertEquals(0.0, product.getTotalPrice(), 0.01);
        assertEquals("BANANA ($1.50 x 0) = $0.00", product.toString());
    }

    // Create Product with negative quantity
    @Test
    public void test_create_product_with_negative_quantity() {
        Product product = new Product(ProductType.BANANA, -5);

        assertEquals(-7.5, product.getTotalPrice(), 0.01);
        assertEquals("BANANA ($1.50 x -5) = $-7.50", product.toString());
    }

    // Get total price for quantity of 1
    @Test
    public void test_get_total_price_for_quantity_one() {
        Product product = new Product(ProductType.BANANA, 1);

        assertEquals(1.5, product.getTotalPrice(), 0.01);
        assertEquals("BANANA ($1.50 x 1) = $1.50", product.toString());
    }

    // Test all different ProductType enum values
    @Test
    public void test_product_total_price_for_all_product_types() {
        Product appleProduct = new Product(ProductType.APPLE, 2);
        assertEquals(4.0, appleProduct.getTotalPrice(), 0.01);
        assertEquals("APPLE ($2.00 x 2) = $4.00", appleProduct.toString());

        Product bananaProduct = new Product(ProductType.BANANA, 3);
        assertEquals(4.5, bananaProduct.getTotalPrice(), 0.01);
        assertEquals("BANANA ($1.50 x 3) = $4.50", bananaProduct.toString());

        Product chocolateProduct = new Product(ProductType.CHOCOLATE, 1);
        assertEquals(3.0, chocolateProduct.getTotalPrice(), 0.01);
        assertEquals("CHOCOLATE ($3.00 x 1) = $3.00", chocolateProduct.toString());

        Product breadProduct = new Product(ProductType.BREAD, 5);
        assertEquals(6.0, breadProduct.getTotalPrice(), 0.01);
        assertEquals("BREAD ($1.20 x 5) = $6.00", breadProduct.toString());
    }

    // Test toString with very large quantity numbers
    @Test
    public void test_to_string_with_large_quantity() {
        Product product = new Product(ProductType.BANANA, 1000000);

        assertEquals(1500000.0, product.getTotalPrice(), 0.01);
        assertEquals("BANANA ($1.50 x 1000000) = $1500000.00", product.toString());
    }
}