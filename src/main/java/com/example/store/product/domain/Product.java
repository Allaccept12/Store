package com.example.store.product.domain;


import com.example.store.common.converter.MoneyConverter;
import com.example.store.common.model.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @EmbeddedId
    private ProductId id;

    @Column(name = "price")
    @Convert(converter = MoneyConverter.class)
    private Money price;

    @Column(name = "name")
    private String name;

    protected Product(ProductId productId,Money price, String name) {
        this.id = productId;
        this.price = price;
        this.name = name;
    }

    public static Product createProduct(ProductId productId, Money price, String name) {
        return new Product(productId,price,name);
    }
}
