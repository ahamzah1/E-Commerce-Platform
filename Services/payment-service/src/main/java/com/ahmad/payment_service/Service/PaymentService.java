package com.ahmad.payment_service.Service;

import com.ahmad.payment_service.DTO.PaymentRequest;
import com.ahmad.payment_service.Entity.Payment;
import com.ahmad.payment_service.Kafka.NotificationProducer;
import com.ahmad.payment_service.Kafka.PaymentConfirmation;
import com.ahmad.payment_service.Repository.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;

    @Autowired
    public PaymentService(NotificationProducer notificationProducer, PaymentRepository paymentRepository) {
        this.notificationProducer = notificationProducer;
        this.paymentRepository = paymentRepository;
    }

    public Integer createPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment(paymentRequest.getAmount(), paymentRequest.getPaymentMethod(), paymentRequest.getOrderId());
        payment = paymentRepository.save(payment);

        notificationProducer.SendPaymentConfirmation(new PaymentConfirmation(paymentRequest));

        return payment.getId();
    }
}
