package com.matimbe.ecommerce.payment;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
         String id,
         @NotNull( message="First name is required")
         String first_name,
         @NotNull( message="Last name is required")
         String last_name,
         @NotNull( message=" Email name is required")
         @Email( message="The customer email is not  corrrectly formated")
         String email

) {


}
