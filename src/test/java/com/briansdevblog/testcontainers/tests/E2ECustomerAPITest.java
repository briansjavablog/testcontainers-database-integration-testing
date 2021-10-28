package com.briansdevblog.testcontainers.tests;

import com.briansdevblog.testcontainers.CustomMySqlContainer;
import com.briansdevblog.testcontainers.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class E2ECustomerAPITest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Container
	public static CustomMySqlContainer mySqlContainer =  CustomMySqlContainer.getInstance()
															.withInitScript("database/customer-schema.sql");

	@Test
	public void should_returnCustomer_forCustomerId_1() {

		ResponseEntity<Customer> customerResponse = restTemplate.getForEntity("/api/customer/1", Customer.class);

		assertThat(customerResponse.getStatusCode(), is(HttpStatus.OK));

		assertThat(customerResponse.getBody().getFirstName(), is("Joe"));
		assertThat(customerResponse.getBody().getLastName(), is("Bloggs"));
		assertThat(customerResponse.getBody().getDateOfBirth(), is(LocalDate.of(1983, 5, 17)));
		assertThat(customerResponse.getBody().getGender(), is("male"));
	}

	@Test
	public void should_saveCustomer_andReturnNewEntity() {

		Customer customer = new Customer();
		customer.setFirstName("Jane");
		customer.setLastName("Doe");
		customer.setDateOfBirth(LocalDate.of(1981, 5, 9));
		customer.setGender("female");

		HttpEntity customerEntity = new HttpEntity<>(customer);

		ResponseEntity<Customer> savedCustomerResponse = restTemplate.postForEntity("/api/customer", customerEntity, Customer.class);

		assertThat(savedCustomerResponse.getBody().getId(), is(notNullValue()));
		assertThat(savedCustomerResponse.getBody().getFirstName(), is("Jane"));
		assertThat(savedCustomerResponse.getBody().getLastName(), is("Doe"));
		assertThat(savedCustomerResponse.getBody().getDateOfBirth(), is(LocalDate.of(1981, 5, 9)));
		assertThat(savedCustomerResponse.getBody().getGender(), is("female"));
	}

}