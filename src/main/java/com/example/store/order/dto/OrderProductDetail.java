package com.example.store.order.dto;


import lombok.Getter;

@Getter
public class OrderProductDetail {

    private String productName;
    private int quantity;
    private int price;

    public OrderProductDetail() {
    }

    public OrderProductDetail(String productName, int quantity, int price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
