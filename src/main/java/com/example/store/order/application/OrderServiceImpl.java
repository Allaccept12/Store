package com.example.store.order.application;

import com.example.store.account.application.AccountService;
import com.example.store.account.domain.Account;
import com.example.store.order.domain.Order;
import com.example.store.order.domain.OrderLine;
import com.example.store.order.domain.OrderRepository;
import com.example.store.order.dto.OrderHistory;
import com.example.store.order.dto.OrderProduct;
import com.example.store.order.dto.OrderProductDetail;
import com.example.store.order.dto.OrderReq;
import com.example.store.product.domain.Product;
import com.example.store.product.domain.ProductId;
import com.example.store.product.domain.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final AccountService accountService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long order(OrderReq orderReq) {
        Account account = accountService.getAccount(orderReq.getAccountId());
        List<OrderLine> orderLines = new ArrayList<>();

        for (OrderProduct op :orderReq.getOrderProducts()) {
            Product product = productRepository.findById(ProductId.of(op.getProductId()))
                    .orElseThrow(NotFoundProductException::new);
            orderLines.add(OrderLine.createOrderLine(product.getId(), product.getName(), op.getQuantity(),product.getPrice()));
        }

        Order order = Order.createOrder(account, orderLines);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    public List<OrderHistory> getUserOrderHistories(Long accountId) {
        Account account = accountService.getAccount(accountId);
        List<Order> orderList = orderRepository.findByAccountId(account.getId());
        List<OrderHistory> histories = new ArrayList<>();

        for (Order order: orderList) {
            List<OrderProductDetail> orderProductDetails = new ArrayList<>();
            for (OrderLine ol : order.getOrderLines()) {
                OrderProductDetail opd =
                        new OrderProductDetail(ol.getProductName(), ol.getQuantity(), ol.getPrice().getValue());
                orderProductDetails.add(opd);
            }
            OrderHistory orderHistory =
                    new OrderHistory(order.getId(), orderProductDetails, order.getTotalAmounts().getValue(), order.getOrderDate());
            histories.add(orderHistory);
        }

        return histories;
    }
}
