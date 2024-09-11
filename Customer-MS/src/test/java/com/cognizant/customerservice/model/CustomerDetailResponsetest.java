package com.cognizant.customerservice.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.model.CustomerDetailsResponse;


class CustomerDetailResponsetest {

	CustomerDetailsResponse customer = new CustomerDetailsResponse();
	CustomerDetailsResponse customer2 = new CustomerDetailsResponse("103","bc","bc",new Date(0),"123","xyz",null);

	@Test
	void setUserIdTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void setUserNameTest() {
		customer.setUsername("santhu");
		assertEquals("santhu", customer.getUsername());
	}

	@Test
	void setPasswordTest() {
		customer.setPassword("abc");
		assertEquals("abc", customer.getPassword());
	}

	@Test
	void setAddressTest() {
		customer.setAddress("PQR");
		assertEquals("PQR", customer.getAddress());
	}

	@Test
	void setPanTest() {
		customer.setPan("ABCDE1234S");
		assertEquals("ABCDE1234S", customer.getPan());
	}

	@Test
	void setDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

	@Test
	void getAccTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void getUserNameTest() {
		customer.setUsername("Santhu");
		assertEquals("Santhu", customer.getUsername());
	}

	@Test
	void getPasswordTest() {
		customer.setPassword("xyz");
		assertEquals("xyz", customer.getPassword());
	}

	@Test
	void getAddressTest() {
		customer.setAddress("pqr");
		assertEquals("pqr", customer.getAddress());
	}

	@Test
	void getPanTest() {
		customer.setPan("abcde1234s");
		assertEquals("abcde1234s", customer.getPan());
	}

	@Test
	void getDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

}
