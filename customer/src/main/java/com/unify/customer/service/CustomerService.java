package com.unify.customer.service;

import com.unify.customer.config.CustomerConfig;
import com.unify.customer.domain.Customer;
import com.unify.customer.repository.CustomerRepository;
import com.unify.customer.web.rest.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        // todo: check if email is valid
        // todo: check if email is not taken
        // todo: store customer in db
        customerRepository.saveAndFlush(customer);

        // todo: check if customer is fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }


        // todo: send notification
    }
}
