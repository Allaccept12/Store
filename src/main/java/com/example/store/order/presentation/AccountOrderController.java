package com.example.store.order.presentation;


import com.example.store.order.application.OrderService;
import com.example.store.order.dto.OrderHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountOrderController {

    private final OrderService orderService;

    @GetMapping("/account/orders/{accountId}")
    public ResponseEntity<?> getAccountOrders(@PathVariable Long accountId) {
        List<OrderHistory> userOrderHistories = orderService.getUserOrderHistories(accountId);
        return ResponseEntity.ok(userOrderHistories);
    }
}
