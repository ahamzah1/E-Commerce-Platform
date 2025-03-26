package com.example.ahmad.OrderService.DTO;

import com.example.ahmad.OrderService.Entity.Order;
import com.example.ahmad.OrderService.Entity.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

public class OrderResponse {

    private Integer id;
    private String reference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerId;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.reference = order.getReference();
        this.amount = order.getTotalAmount();
        this.paymentMethod = order.getPaymentMethod();
        this.customerId = order.getCustomerId();
    }
}
