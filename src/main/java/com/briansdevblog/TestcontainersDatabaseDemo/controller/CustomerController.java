package com.briansdevblog.TestcontainersDatabaseDemo.controller;

import com.briansdevblog.TestcontainersDatabaseDemo.entity.Customer;
import com.briansdevblog.TestcontainersDatabaseDemo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class CustomerController {

    private CustomerRepository customerRepository;

    @PostMapping(path = "/api/customer")
    public @ResponseBody Customer createCustomer(Customer customer){

        log.info("saving [{}]", customer);
        Customer persistedCustomer = customerRepository.save(customer);
        log.info("returning [{}]", persistedCustomer);

        return persistedCustomer;
    }

    @GetMapping(path = "/api/customer/{id}")
    public Customer getCustomer(@PathVariable("{id}") Long customerId){

        log.info("retrieving customer Id [{}]", customerId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        log.info("returning [{}]", customer);

        return customer.orElseThrow(() -> new RuntimeException("customer not found for Id " + customerId));
    }

}
