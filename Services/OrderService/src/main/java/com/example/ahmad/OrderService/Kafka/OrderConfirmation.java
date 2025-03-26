package com.example.ahmad.OrderService.Kafka;

import com.example.ahmad.OrderService.Communication.CustomerResponse;
import com.example.ahmad.OrderService.Communication.ProductPurchaseResponse;
import com.example.ahmad.OrderService.Entity.PaymentMethod;
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
