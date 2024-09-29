package com.matimbe.ecommerce.kafka;

import com.matimbe.ecommerce.customer.CustomerResponse;
import com.matimbe.ecommerce.order.PaymentMethod;
import com.matimbe.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
