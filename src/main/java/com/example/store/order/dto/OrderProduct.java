package com.example.store.order.dto;

import com.example.store.product.domain.ProductId;
import lombok.Getter;

@Getter
public class OrderProduct {

    private String productId;
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
