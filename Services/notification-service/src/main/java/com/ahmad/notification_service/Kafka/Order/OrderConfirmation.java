package com.ahmad.notification_service.Kafka.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderConfirmation {
    private String orderReference;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private CustomerResponse customer;

    private List<ProductPurchaseResponse> productPurchase;

}
