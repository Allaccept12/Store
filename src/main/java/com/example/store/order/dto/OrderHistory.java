package com.example.store.order.dto;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderHistory {

    private Long orderId;
    private List<OrderProductDetail> orderProductDetails;
    private int totalAmounts;
    private LocalDateTime orderDate;

    public OrderHistory() {
    }

    public OrderHistory(Long orderId, List<OrderProductDetail> orderProductDetails, int totalAmounts,
                        LocalDateTime orderDate) {
        this.orderId = orderId;
        this.orderProductDetails = orderProductDetails;
        this.totalAmounts = totalAmounts;
        this.orderDate = orderDate;
    }
}
