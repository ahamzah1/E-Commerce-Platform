package com.example.ahmad.OrderService.Entity;

import com.example.ahmad.OrderService.Communication.ProductPurchaseResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderLine {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int productId;
    private double quantity;

    public OrderLine(ProductPurchaseResponse input , Order order) {
        this.productId = input.getProductId();
        this.order = order;
        this.quantity = input.getQuantity();
    }
}
