package com.example.store.order.domain;


import com.example.store.account.domain.Account;
import com.example.store.common.converter.MoneyConverter;
import com.example.store.common.model.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_line",
            joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderLine> orderLines;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "total_amounts")
    private Money totalAmounts;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    protected Order(Account account, List<OrderLine> orderLines) {
        this.account = account;
        this.orderLines = orderLines;
        this.totalAmounts = calculateTotalAmounts();
        this.orderDate = LocalDateTime.now();
    }

    public static Order createOrder(Account account, List<OrderLine> orderLines) {
        return new Order(account,orderLines);
    }

    private Money calculateTotalAmounts() {
        return Money.of(orderLines.stream()
                .mapToInt(ol -> ol.getAmounts().getValue())
                .sum());
    }

}













