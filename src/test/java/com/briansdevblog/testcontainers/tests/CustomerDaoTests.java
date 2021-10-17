package com.briansdevblog.testcontainers.tests;

import com.briansdevblog.testcontainers.CustomMySqlContainer;
import com.briansdevblog.testcontainers.entity.Customer;
import com.briansdevblog.testcontainers.dao.CustomerDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@Testcontainers
class CustomerDaoTests {

	@Autowired
	private CustomerDao customerDao;

	@Container
	public static CustomMySqlContainer mySqlContainer =  CustomMySqlContainer.getInstance()
															.withInitScript("database/customer-schema.sql");

	@Test
	public void should_returnCustomers_with_LastNameBloggs() {

		List<Customer> customers = customerDao.findByLastName("Bloggs");

		assertThat(customers.get(0).getFirstName(), is("Joe"));
		assertThat(customers.get(0).getLastName(), is("Bloggs"));
		assertThat(customers.get(0).getDateOfBirth(), is(LocalDate.of(1983, 5, 17)));
		assertThat(customers.get(0).getGender(), is("male"));
	}

	@Test
	public void should_returnSavedCustomer() {

		Customer customer = new Customer();
		customer.setFirstName("Jane");
		customer.setLastName("Doe");
		customer.setDateOfBirth(LocalDate.of(1981, 5, 9));
		customer.setGender("female");

		customerDao.save(customer);

		List<Customer> customers = customerDao.findByLastName("Doe");

		assertThat(customers.get(0).getFirstName(), is("Jane"));
		assertThat(customers.get(0).getLastName(), is("Doe"));
		assertThat(customers.get(0).getDateOfBirth(), is(LocalDate.of(1981, 5, 9)));
		assertThat(customers.get(0).getGender(), is("female"));
	}

}