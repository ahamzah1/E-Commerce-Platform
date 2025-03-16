package com.ahmad.productService.Controllers;

import com.ahmad.productService.DTO.ProductPurchaseRequest;
import com.ahmad.productService.DTO.ProductPurchaseResponse;
import com.ahmad.productService.DTO.ProductRequest;
import com.ahmad.productService.DTO.ProductResponse;
import com.ahmad.productService.Entity.Product;
import com.ahmad.productService.Repository.ProductRepository;
import com.ahmad.productService.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> handleAdd(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.handleAdd(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> getPurchase(@Valid @RequestBody List<ProductPurchaseRequest> inp){
        return ResponseEntity.ok(productService.handlePurchase(inp));
    }

    @GetMapping("{product-id}")
    public ResponseEntity<ProductResponse> getProductId(@PathVariable("product-id") Integer id){
        return ResponseEntity.ok(productService.handleGetId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(productService.handleGetAll());
    }

}
