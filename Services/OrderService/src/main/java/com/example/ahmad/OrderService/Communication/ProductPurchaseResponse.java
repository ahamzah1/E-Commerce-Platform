package com.example.ahmad.OrderService.Communication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseResponse {
    private int productId;

    private String name;

    private String description;

    private BigDecimal price;

    private double quantity;

}