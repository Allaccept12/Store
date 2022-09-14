package com.example.store.order.domain;


import com.example.store.common.converter.MoneyConverter;
import com.example.store.common.model.Money;
import com.example.store.product.domain.Product;
import com.example.store.product.domain.ProductId;
import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
public class OrderLine {

    @Embedded
    private ProductId productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    @Convert(converter = MoneyConverter.class)
    private Money price;

    @Column(name = "amounts")
    @Convert(converter = MoneyConverter.class)
    private Money amounts;

    protected OrderLine() {
    }

    protected OrderLine(ProductId productId,String productName, int quantity, Money price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateTotalAmounts();
    }

    private Money calculateTotalAmounts() {
        return price.multiply(this.quantity);
    }

    public static OrderLine createOrderLine(ProductId productId,String productName, int quantity, Money price) {
        return new OrderLine(productId,productName,quantity,price);
    }
}
