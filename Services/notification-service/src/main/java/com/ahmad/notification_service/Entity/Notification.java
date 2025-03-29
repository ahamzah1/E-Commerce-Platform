package com.ahmad.notification_service.Entity;

import com.ahmad.notification_service.Kafka.Order.OrderConfirmation;
import com.ahmad.notification_service.Kafka.Payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Notification {

    @Id
    private String id;

    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}
