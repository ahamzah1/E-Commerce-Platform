package com.example.ahmad.OrderService.Communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}",
        configuration = FeignClientConfig.class
)
public interface PaymentClient {

    @PostMapping
    Integer createPayment(@RequestBody PaymentRequest paymentRequest);
}
