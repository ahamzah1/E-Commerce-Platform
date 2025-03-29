package com.ahmad.notification_service.Kafka;

import com.ahmad.notification_service.Email.EmailService;
import com.ahmad.notification_service.Entity.Notification;
import com.ahmad.notification_service.Entity.NotificationType;
import com.ahmad.notification_service.Kafka.Order.CustomerResponse;
import com.ahmad.notification_service.Kafka.Order.OrderConfirmation;
import com.ahmad.notification_service.Kafka.Order.PaymentMethod;
import com.ahmad.notification_service.Kafka.Payment.PaymentConfirmation;
import com.ahmad.notification_service.Repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ahmad.notification_service.Entity.NotificationType.ORDER_CONFIRMATION;
import static com.ahmad.notification_service.Entity.NotificationType.PAYMENT_CONFIRMATION;

@Service
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @Autowired
    public NotificationConsumer(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic Topic::{}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        CustomerResponse customer = orderConfirmation.getCustomer();
        String fullName = customer.getFirstname() + " " + customer.getLastname();
        emailService.sendOrderConfirmationEmail(customer.getEmail(),fullName, orderConfirmation.getAmount(), orderConfirmation.getOrderReference(), orderConfirmation.getProductPurchase());
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic Topic::{}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        String fullName = paymentConfirmation.getFirstName() + " " + paymentConfirmation.getLastName();
        emailService.sendPaymentSuccessEmail(paymentConfirmation.getEmail(),fullName, paymentConfirmation.getAmount(), paymentConfirmation.getOrderReference());
    }
}
