package com.example.store.order.presentation;


import com.example.store.order.application.OrderService;
import com.example.store.order.dto.OrderReq;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "상품 주문")
    @PostMapping("/orders/order")
    public ResponseEntity<?> order(@RequestBody @Valid OrderReq req) {
        return ResponseEntity.ok(orderService.order(req));
    }


}
