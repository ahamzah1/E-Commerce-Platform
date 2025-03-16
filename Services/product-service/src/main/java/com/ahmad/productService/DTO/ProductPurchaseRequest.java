package com.ahmad.productService.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseRequest {
    @NotNull
    int productId;
    @NotNull
    double quantity;

}
