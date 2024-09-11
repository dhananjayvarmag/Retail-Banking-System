package com.cognizant.accountservice.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.cognizant.accountservice.model.CustomerEntity;


class CustomerEntityTest {

	CustomerEntity customer = new CustomerEntity();

	@Test
	void setUserIdTest() {
		customer.setUserid("CUSTOMER101");
		assertEquals("CUSTOMER101", customer.getUserid());
	}

	@Test
	void setUserNameTest() {
		customer.setUsername("customer 101");
		assertEquals("customer 101", customer.getUsername());
	}

	@Test
	void setPasswordTest() {
		customer.setPassword("password123");
		assertEquals("password123", customer.getPassword());
	}

	@Test
	void setAddressTest() {
		customer.setAddress("Bhimavaram");
		assertEquals("Bhimavaram", customer.getAddress());
	}

	@Test
	void setPanTest() {
		customer.setPan("ABCDE1234R");
		assertEquals("ABCDE1234R", customer.getPan());
	}

	@Test
	void setDateTest() {
		Date d = new Date(1);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

	@Test
	void getAccTest() {
		customer.setUserid("CUSTOMER102");
		assertEquals("CUSTOMER102", customer.getUserid());
	}

	@Test
	void getUserNameTest() {
		customer.setUsername("customer 102");
		assertNotEquals("customer 103", customer.getUsername());
	}

	@Test
	void getPasswordTest() {
		customer.setPassword("pwd");
		assertEquals("pwd", customer.getPassword());
	}

	@Test
	void getAddressTest() {
		customer.setAddress("Hyderabad");
		assertEquals("Hyderabad", customer.getAddress());
	}

	@Test
	void getPanTest() {
		customer.setPan("CQJPG6345P");
		assertEquals("CQJPG6345P", customer.getPan());
	}

	@Test
	void getDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

	

	


}
