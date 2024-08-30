package com.matimbe.ecommerce.order;

import com.matimbe.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String references,
        @Positive(message =  "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer  should be present")
        @NotEmpty(message = "Customer  should be present")
        @Positive(message = "Customer  should be present")
        String customerId,
        @NotEmpty(message = "Payment method should be precised")
        List<PurchaseRequest> products
) {
}
