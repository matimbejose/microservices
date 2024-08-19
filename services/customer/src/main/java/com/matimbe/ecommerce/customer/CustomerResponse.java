package com.matimbe.ecommerce.customer;

import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String id,
        String first_name,
        String last_name,
        String email,
        Address address
) {

}
