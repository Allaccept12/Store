package com.example.store.product.application;


import lombok.Getter;

@Getter
public class ProductDetailRes {

    private int price;
    private String name;

    public ProductDetailRes() {
    }

    public ProductDetailRes(int price, String name) {
        this.price = price;
        this.name = name;
    }
}
