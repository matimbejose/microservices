package com.matimbe.ecommerce.customer;

public record CustomerResponse(
         String id,
         String first_name,
         String last_name,
         String email
) {
}
