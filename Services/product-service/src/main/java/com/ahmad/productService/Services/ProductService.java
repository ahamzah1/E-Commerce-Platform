package com.ahmad.productService.Services;

import com.ahmad.productService.Controllers.ProductController;
import com.ahmad.productService.DTO.ProductPurchaseRequest;
import com.ahmad.productService.DTO.ProductPurchaseResponse;
import com.ahmad.productService.DTO.ProductRequest;
import com.ahmad.productService.DTO.ProductResponse;
import com.ahmad.productService.Entity.Category;
import com.ahmad.productService.Entity.Product;
import com.ahmad.productService.Exceptions.CategoryNotFoundException;
import com.ahmad.productService.Exceptions.ProductNotFoundException;
import com.ahmad.productService.Exceptions.ProductPurchaseException;
import com.ahmad.productService.Mappers.ProductMapper;
import com.ahmad.productService.Repository.CategoryRepository;
import com.ahmad.productService.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public Integer handleAdd(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.categoryId()).orElseThrow(()->
                new CategoryNotFoundException(String.format("Category Not found for Id %s", productRequest.categoryId())));

        Product res = productRepository.save(productMapper.toProductFromRequest(productRequest,category));
        return res.getId();
    }

    public ProductResponse handleGetId(Integer id) {
        Product res = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException(String.format("Product Not found for Id %s", id)));
        return productMapper.toProductResponse(res);
    }


    public List<ProductResponse> handleGetAll() {
        return productRepository.findAll().stream().map(item -> productMapper.toProductResponse(item)).collect(Collectors.toList());
    }

    @Transactional
    public List<ProductPurchaseResponse> handlePurchase(List<ProductPurchaseRequest> inp) {
        List<Integer> productIds = inp.stream().map(ProductPurchaseRequest::getProductId).toList();

        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(storedProducts.size() != productIds.size()){
            throw new ProductPurchaseException("One or more products do not exist!");
        }

        List<ProductPurchaseRequest> storedRequest = inp.stream().sorted(Comparator.comparing(ProductPurchaseRequest::getProductId)).toList();
        List<ProductPurchaseResponse> purchaseProducts = new ArrayList<>();
        for(int i = 0; i< productIds.size();i++){
            Product product = storedProducts.get(i);
            ProductPurchaseRequest request = storedRequest.get(i);
            if(request.getQuantity() > product.getAvailable_quantity()){
                purchaseProducts.add(new ProductPurchaseResponse(product, 0.0));
                continue;
            }
            Product res = productRepository.findById(product.getId()).orElseThrow();
            res.setAvailable_quantity(res.getAvailable_quantity() - request.getQuantity());
            productRepository.save(res);
            purchaseProducts.add(new ProductPurchaseResponse(product, request.getQuantity()));

        }
        return purchaseProducts;
    }
    @Transactional
    public void revert(List<ProductPurchaseRequest> inp) {
        List<Integer> productIds = inp.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        // Fetch all products in one query
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (storedProducts.size() != productIds.size()) {
            throw new ProductPurchaseException("One or more products do not exist!");
        }

        // Convert to a Map for O(1) lookups (better than sorting + index matching)
        Map<Integer, Product> productMap = storedProducts.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        // Update quantities
        for (ProductPurchaseRequest request : inp) {
            Product product = productMap.get(request.getProductId());
            if (product == null) {
                throw new ProductPurchaseException("Product not found: " + request.getProductId());
            }
            product.setAvailable_quantity(product.getAvailable_quantity() + request.getQuantity());
        }

        // Save all in one batch (more efficient than individual saves)
        productRepository.saveAll(storedProducts);
    }
}
