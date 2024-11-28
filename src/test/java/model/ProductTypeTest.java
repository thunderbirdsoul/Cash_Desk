package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTypeTest {
    // Verify each enum constant has correct name value
    @Test
    public void test_enum_constants_have_correct_names() {
        assertEquals("Apple", ProductType.APPLE.getName());
        assertEquals("Banana", ProductType.BANANA.getName());
        assertEquals("Chocolate", ProductType.CHOCOLATE.getName());
        assertEquals("Bread", ProductType.BREAD.getName());
    }

    // Verify each enum constant has correct price value
    @Test
    public void test_enum_constants_have_correct_prices() {
        assertEquals(2.0, ProductType.APPLE.getPrice(), 0.001);
        assertEquals(1.5, ProductType.BANANA.getPrice(), 0.001);
        assertEquals(3.0, ProductType.CHOCOLATE.getPrice(), 0.001);
        assertEquals(1.2, ProductType.BREAD.getPrice(), 0.001);
    }

    // Test valueOf() with non-existent product name throws IllegalArgumentException
    @Test
    public void test_value_of_with_invalid_name_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            ProductType.valueOf("ORANGE");
        });
    }

    // Test valueOf() with null throws NullPointerException
    @Test
    public void test_value_of_with_null_throws_exception() {
        assertThrows(NullPointerException.class, () -> {
            ProductType.valueOf(null);
        });
    }

    // Verify price values handle decimal precision correctly
    @Test
    public void test_price_decimal_precision() {
        double breadPrice = ProductType.BREAD.getPrice();
        assertEquals(1.20, breadPrice, 0.0001);
        double bananaPrice = ProductType.BANANA.getPrice();
        assertEquals(1.50, bananaPrice, 0.0001);
    }

    // Verify getPrice() returns expected double for each product type
    @Test
    public void test_product_type_prices() {
        assertEquals(2.0, ProductType.APPLE.getPrice());
        assertEquals(1.5, ProductType.BANANA.getPrice());
        assertEquals(3.0, ProductType.CHOCOLATE.getPrice());
        assertEquals(1.2, ProductType.BREAD.getPrice());
    }
}