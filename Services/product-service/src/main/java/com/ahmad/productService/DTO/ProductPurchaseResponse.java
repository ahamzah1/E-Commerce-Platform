package com.ahmad.productService.DTO;

import com.ahmad.productService.Entity.Product;
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

    public ProductPurchaseResponse(Product product, double quantity){
        this.productId = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = quantity;

    }
}
