package com.matimbe.ecommerce.email;


import com.matimbe.ecommerce.kafka.payment.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.math.BigDecimal;
import org.thymeleaf.context.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static com.matimbe.ecommerce.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.matimbe.ecommerce.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;


    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MULTIPART_MODE_RELATED, UTF_8.name());
            messageHelper.setFrom("matinbejose@gmail.com");

            final String templateName = PAYMENT_CONFIRMATION.getTemplate();


            Map<String, Object> variables  = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);

            Context  context = new Context();
            context.setVariables(variables);
            messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

            try {
                String htmlTemplate = templateEngine.process(templateName, context);
                messageHelper.setText(htmlTemplate, true);

                messageHelper.setTo(destinationEmail);
                mailSender.send(mimeMessage);
                log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
            } catch (MessagingException e) {
                log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
            }




        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    @Async
    public void sentOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MULTIPART_MODE_RELATED, UTF_8.name());
            messageHelper.setFrom("matinbejose@gmail.com");

            final String templateName = ORDER_CONFIRMATION.getTemplate();


            Map<String, Object> variables  = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("totalAmount", amount);
            variables.put("orderReference", orderReference);
            variables.put("products", products);

            Context  context = new Context();
            context.setVariables(variables);
            messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

            try {
                String htmlTemplate = templateEngine.process(templateName, context);
                messageHelper.setText(htmlTemplate, true);

                messageHelper.setTo(destinationEmail);
                mailSender.send(mimeMessage);
                log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
            } catch (MessagingException e) {
                log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
            }




        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }




}
