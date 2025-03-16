package com.ahmad.productService.Mappers;

import com.ahmad.productService.DTO.ProductRequest;
import com.ahmad.productService.DTO.ProductResponse;
import com.ahmad.productService.Entity.Category;
import com.ahmad.productService.Entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProductFromRequest(ProductRequest productRequest, Category category){
        return Product.builder()
                .name(productRequest.name())
                .available_quantity(productRequest.available_quantity())
                .description(productRequest.description())
                .price(productRequest.price())
                .category(category)
                .build();
    }

    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(product);
    }


}
