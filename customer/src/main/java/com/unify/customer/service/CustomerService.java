package com.unify.customer.service;

import com.unify.customer.domain.Customer;
import com.unify.customer.web.rest.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void registerCustomer(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        // todo: check if email is valid
        // todo: check if email is not taken
        // todo: store customer in db
    }
}
