package com.cognizant.customerservice.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.model.CustomerEntity;


class CustomerEntityTest {

	CustomerEntity customer = new CustomerEntity();
	CustomerEntity customer2 = new CustomerEntity("105","bcd","bcd",new Date(0),"123","xyz",null);

	@Test
	void setUserIdTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void setUserNameTest() {
		customer.setUsername("Juhi");
		assertEquals("Juhi", customer.getUsername());
	}

	@Test
	void setPasswordTest() {
		customer.setPassword("abcd");
		assertEquals("abcd", customer.getPassword());
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
		customer.setUsername("Juhi");
		assertEquals("Juhi", customer.getUsername());
	}

	@Test
	void getPasswordTest() {
		customer.setPassword("abcd");
		assertEquals("abcd", customer.getPassword());
	}

	@Test
	void getAddressTest() {
		customer.setAddress("Pqr");
		assertEquals("Pqr", customer.getAddress());
	}

	@Test
	void getPanTest() {
		customer.setPan("abcd1234s");
		assertEquals("abcd1234s", customer.getPan());
	}

	@Test
	void getDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

}
