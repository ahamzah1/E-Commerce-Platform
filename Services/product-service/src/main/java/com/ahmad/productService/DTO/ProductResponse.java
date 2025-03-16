package com.ahmad.productService.DTO;

import com.ahmad.productService.Entity.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

        private Integer id;

        @NotNull(message = "Product name is required")
        private String name;

        @NotNull(message = "Product Description is required")
        private String description;

        @Positive(message = "Available Quantity should be positive")
        private double availableQuantity;

        @Positive(message = "Product Price should be positive")
        private BigDecimal price;

        @NotNull(message = "Product Category is required")
        private Integer categoryId;

        // Custom constructor that takes in a Product class
        public ProductResponse(Product product) {
                this.id = product.getId();
                this.name = product.getName();
                this.description = product.getDescription();
                this.availableQuantity = product.getAvailable_quantity();
                this.price = product.getPrice();
                this.categoryId = product.getCategory().getId();
        }
}

