package com.example.store.order.application;

import com.example.store.order.dto.OrderHistory;
import com.example.store.order.dto.OrderReq;

import java.util.List;

public interface OrderService {

    Long order(OrderReq orderReq);

    List<OrderHistory> getUserOrderHistories(Long accountId);
}
