package com.example.store.order.dto;


import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class OrderReq {

    @NotNull
    @Size(min = 1)
    private List<OrderProduct> orderProducts;

    @NotNull
    private Long accountId;

    public OrderReq() {
    }

    public OrderReq(List<OrderProduct> orderProducts, Long accountId) {
        this.orderProducts = orderProducts;
        this.accountId = accountId;
    }
}
