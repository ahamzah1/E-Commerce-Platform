package com.example.ahmad.OrderService.Communication;

import com.example.ahmad.OrderService.DTO.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @PostMapping("/purchase")
    List<ProductPurchaseResponse> purchaseProducts(@RequestBody List<PurchaseRequest> requests);

    @PostMapping("/purchase/revert")
    void revertPurchaseProducts(@RequestBody List<PurchaseRequest> requests);

}
