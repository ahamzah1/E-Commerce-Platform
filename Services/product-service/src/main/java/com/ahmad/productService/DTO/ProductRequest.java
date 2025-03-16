package com.ahmad.productService.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest (
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product Description is required")
        String description,
        @Positive(message = "Available Quantity should be positive")
        double available_quantity,
        @Positive(message = "Product Price should be positive")
        BigDecimal price,
        @NotNull(message = "Product Category is required")
        Integer categoryId
){

}
