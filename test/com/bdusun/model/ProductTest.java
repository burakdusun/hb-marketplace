package com.bdusun.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void manipulatePriceByPercentage() {
        Product product = new Product("P1", new BigDecimal(100), 1000);
        product.manipulatePriceByPercentage(2.0);
        assertEquals(product.getPrice(), new BigDecimal(98));
        product.manipulatePriceByPercentage(25.0);
        assertEquals(product.getPrice(), new BigDecimal(75));
        product.manipulatePriceByPercentage(0);
        assertEquals(product.getPrice(), new BigDecimal(100));
        product.manipulatePriceByPercentage(100);
        assertEquals(product.getPrice(), new BigDecimal(100));
        assertThrows(IllegalArgumentException.class, () -> product.manipulatePriceByPercentage(101));
    }
}