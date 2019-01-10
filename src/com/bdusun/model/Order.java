package com.bdusun.model;

public class Order {

    private String productCode;
    private long quantity;


    public Order(String productCode, long quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
        getOrderString();
    }

    private void getOrderString() {
        System.out.println("Order created; product " + productCode + ", quantity " + quantity);
    }

}
