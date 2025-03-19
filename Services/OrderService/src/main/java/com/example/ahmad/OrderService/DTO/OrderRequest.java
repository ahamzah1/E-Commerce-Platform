package com.example.ahmad.OrderService.DTO;

import com.example.ahmad.OrderService.Entity.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Integer id;
    private String reference;
    @Positive(message = "Order amount should be positive")
    private BigDecimal amount;
    @NotNull(message = "Payment method should be precised")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Customer Should be present")
    private String customerId;
    @NotEmpty(message = " You should at least have one item!")
    private List<PurchaseRequest> products;


}
