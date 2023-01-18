package com.example.store.order.application;

import com.example.store.account.domain.Account;
import com.example.store.account.domain.AccountRepository;
import com.example.store.account.domain.Email;
import com.example.store.account.domain.Password;
import com.example.store.common.model.Money;
import com.example.store.order.domain.OrderRepository;
import com.example.store.order.dto.OrderHistory;
import com.example.store.order.dto.OrderProduct;
import com.example.store.order.dto.OrderReq;
import com.example.store.product.domain.Product;
import com.example.store.product.domain.ProductId;
import com.example.store.product.domain.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;
    

    @BeforeEach
    void setting() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void order(){

        Account account = Account.createAccount(Email.of("email"), Password.of("123123"));
        accountRepository.save(account);
        productRepository.save(Product.createProduct(ProductId.of("product_1"), Money.of(100), "testProduct"));
        OrderReq orderReq = new OrderReq(List.of(new OrderProduct("product_1",1)),account.getId());
        //when
        Long result = orderService.order(orderReq);
        //then
        Assertions.assertEquals(1L,result);
    }

    @Test
    void getUserOrderHistories() {

        Account account = Account.createAccount(Email.of("email"), Password.of("123123"));
        accountRepository.save(account);
        productRepository.save(Product.createProduct(ProductId.of("product_1"), Money.of(100), "testProduct"));
        OrderReq orderReq = new OrderReq(List.of(new OrderProduct("product_1",1)),account.getId());
        Long result = orderService.order(orderReq);

        List<OrderHistory> userOrderHistories = orderService.getUserOrderHistories(account.getId());
        Assertions.assertEquals(userOrderHistories.get(0).getOrderId(), result);

    }
}