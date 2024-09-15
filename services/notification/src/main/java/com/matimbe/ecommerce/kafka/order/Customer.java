package com.matimbe.ecommerce.kafka.order;

public record Customer(
        String first_name,
        String last_name,
        String email
) {


}
