package com.ahmad.notification_service.Kafka.Payment;

import com.ahmad.notification_service.Kafka.Order.PaymentMethod;
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

}