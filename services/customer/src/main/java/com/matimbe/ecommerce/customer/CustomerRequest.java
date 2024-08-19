package com.matimbe.ecommerce.customer;

import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        String first_name,
        @NotNull(message = "Customer lastname is required")
        String last_name,
        @NotNull(message = "Customer email is required")
        @NotNull(message = "Customer email is not a valid email address")
        String email,
        @NotNull(message = "Customer firstname is required")
        Address address
) {

}
