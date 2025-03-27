package com.ahmad.payment_service.Kafka;

import com.ahmad.payment_service.DTO.Customer;
import com.ahmad.payment_service.DTO.PaymentRequest;
import com.ahmad.payment_service.Entity.Payment;
import com.ahmad.payment_service.Entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentConfirmation {

    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String orderReference;
    private String firstName;
    private String lastName;
    private  String email;

    public PaymentConfirmation(PaymentRequest payment) {
        this.amount = payment.getAmount();
        this.paymentMethod = payment.getPaymentMethod();
        this.orderReference = payment.getOrderReference();
        Customer customer = payment.getCustomer();
        this.firstName = customer.getFirstname();
        this.lastName = customer.getLastname();
        this.email = customer.getEmail();
    }
}
