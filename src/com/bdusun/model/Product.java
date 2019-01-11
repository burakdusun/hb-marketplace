package com.bdusun.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private String productCode;
    private BigDecimal price;
    private final BigDecimal initialPrice;
    private long stock;

    public Product(String productCode, BigDecimal price, long stock) {
        this.productCode = productCode;
        this.price = price;
        this.stock = stock;

        initialPrice = price;
        printProductMessage(false);
    }


    public boolean isStockEnough(long quantity) {
        return this.stock - quantity >= 0;
    }

    public void printProductMessage(boolean info) {
        String infoOrCreation;
        if (info) {
            infoOrCreation = " info;";
        } else {
            infoOrCreation = " created;";
        }
        System.out.println("Product " + productCode + infoOrCreation + " price " + price + ", stock " + stock);
    }

    public long getStock() { return stock; }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getPriceMargin() {
        return initialPrice.subtract(price);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void manipulatePriceByPercentage(double percentage) {
        if (percentage <= 100) {
            price = initialPrice.subtract((initialPrice.multiply(BigDecimal.valueOf(percentage))).divide(new BigDecimal(100), RoundingMode.UP));
        } else {
            throw new IllegalArgumentException("Percentage must be <= 100");
        }
    }

    public void resetPrice() {
        this.price = initialPrice;
    }

    public void decreaseStock(long stock) {
        this.stock = this.stock - stock;
    }

}
