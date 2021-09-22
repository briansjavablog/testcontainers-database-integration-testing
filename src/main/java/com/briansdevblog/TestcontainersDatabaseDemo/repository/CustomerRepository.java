package com.briansdevblog.TestcontainersDatabaseDemo.repository;

import java.util.List;

import com.briansdevblog.TestcontainersDatabaseDemo.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(final String lastName);
    Customer findById(final long id);
}