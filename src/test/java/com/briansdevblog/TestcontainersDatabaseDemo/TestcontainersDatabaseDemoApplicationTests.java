package com.briansdevblog.TestcontainersDatabaseDemo;

import com.briansdevblog.TestcontainersDatabaseDemo.entity.Customer;
import com.briansdevblog.TestcontainersDatabaseDemo.repository.CustomerDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;


@SpringBootTest
@Testcontainers
class TestcontainersDatabaseDemoApplicationTests {

	@Autowired
	private CustomerDao customerDao;

	@Container
	public static CustomMySqlContainer mySqlContainer =  CustomMySqlContainer.getInstance()
															.withDatabaseName("customers")
															.withInitScript("database/customer-schema.sql");

	@Test
	public void bla() {

		List<Customer> customer = customerDao.findByLastName("Bloggs");

		assertThat(customer, is(customer));
	}

}
