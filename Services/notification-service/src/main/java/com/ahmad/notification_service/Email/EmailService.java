package com.ahmad.notification_service.Email;

import com.ahmad.notification_service.Kafka.Order.ProductPurchaseResponse;
import com.ahmad.notification_service.Kafka.Payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(SpringTemplateEngine templateEngine, JavaMailSender mailSender) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }

    @Async
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount , String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("contact@ahmadcoding.com");
        final String templateName =EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();
        Map<String, Object> map = new HashMap<>();

        map.put("customerName", customerName);
        map.put("amount", amount);
        map.put("orderReference", orderReference);
        Context context = new Context();
        context.setVariables(map);

        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());
        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email Successfully sent to {}", destinationEmail);
        } catch (MessagingException e){
            log.warn("Payment Email failed to send!");
        }

    }

    @Async
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal amount , String orderReference, List<ProductPurchaseResponse> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("contact@ahmadcoding.com");
        final String templateName =EmailTemplates.ORDER_CONFIRMATION.getTemplate();
        Map<String, Object> map = new HashMap<>();

        map.put("customerName", customerName);
        map.put("amount", amount);
        map.put("orderReference", orderReference);
        map.put("products", products);

        Context context = new Context();
        context.setVariables(map);

        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());
        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email Successfully sent to {}", destinationEmail);
        } catch (MessagingException e){
            log.warn("Payment Email failed to send!");
        }

    }
}
