package com.matimbe.ecommerce.payment;

import com.matimbe.ecommerce.customer.CustomerResponse;
import com.matimbe.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {
}
