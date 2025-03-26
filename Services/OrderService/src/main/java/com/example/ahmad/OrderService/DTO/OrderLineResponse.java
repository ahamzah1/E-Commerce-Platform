package com.example.ahmad.OrderService.DTO;

import com.example.ahmad.OrderService.Entity.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderLineResponse {
    private int productId;
    private double quantity;

    public OrderLineResponse(OrderLine orderLine){
        this.quantity = orderLine.getQuantity();
        this.productId = orderLine.getProductId();
    }

}
