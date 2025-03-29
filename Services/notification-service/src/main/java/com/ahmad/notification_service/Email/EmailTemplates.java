package com.ahmad.notification_service.Email;

import lombok.Getter;

@Getter
public enum EmailTemplates {

    PAYMENT_CONFIRMATION("email/payment-confirmation.html", "Payment  processed"),
    ORDER_CONFIRMATION("email/order-confirmation.html", "Order confirmation")

    ;

    private final String template;
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
