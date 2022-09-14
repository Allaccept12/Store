package com.example.store.common.model;


import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
public class Money {

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public static Money of(int value) {
        return new Money(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
