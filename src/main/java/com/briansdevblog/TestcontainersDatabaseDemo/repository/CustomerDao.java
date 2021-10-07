package com.briansdevblog.TestcontainersDatabaseDemo.repository;

import com.briansdevblog.TestcontainersDatabaseDemo.entity.Customer;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerDao {

    private SessionFactory sessionFactory;

    @Transactional
    public List<Customer> findByLastName(final String lastName) {

        Query<Customer> query = sessionFactory.getCurrentSession()
                                               .createQuery("From Customer Where lastName= :lastName")
                                               .setParameter("lastName", lastName);

        return query.list();
    }

    public Optional<Customer> findById(String customerId){

        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Customer.class, customerId));
    }

    public Customer save(Customer customer){

        return (Customer) sessionFactory.getCurrentSession().save(customer);
    }

}