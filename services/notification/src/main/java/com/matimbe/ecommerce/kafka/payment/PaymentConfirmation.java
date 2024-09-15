package com.matimbe.ecommerce.kafka.payment;


import com.matimbe.ecommerce.kafka.order.Customer;

import java.math.BigDecimal;
import java.util.List;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {

}
