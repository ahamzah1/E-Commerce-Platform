package com.example.ahmad.OrderService.Mappers;

import com.example.ahmad.OrderService.DTO.OrderRequest;
import com.example.ahmad.OrderService.Entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest){
        return Order.builder()
                .customerId(orderRequest.getCustomerId())
                .paymentMethod(orderRequest.getPaymentMethod())
                .reference(orderRequest.getReference())
                .totalAmount(orderRequest.getAmount())
                .build();
    }
}
