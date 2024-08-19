package com.matimbe.ecommerce.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerMapper {


    public Customer toCustomer(CustomerRequest request) {
        if(request == null) return null;
        return Customer.builder()
                .id(request.id())
                .first_name(request.first_name())
                .last_name(request.last_name())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return  new CustomerResponse(
                customer.getId(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
