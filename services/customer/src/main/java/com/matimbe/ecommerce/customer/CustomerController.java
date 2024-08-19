package com.matimbe.ecommerce.customer;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/custome")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService service;


    //create customer
    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody  @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    //update customer
    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody  @Valid CustomerRequest request
    ) {
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    //find all customers
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(this.service.findAllCustomers());
    }

    //route for edite customer customer
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(service.existsById(customerId));
    }

    //route for edite customer customer
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(service.findById(customerId));
    }



}
