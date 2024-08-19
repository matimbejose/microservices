package com.matimbe.ecommerce.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document


public class Customer {

    @Id
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private Address address;
}
