package com.rulesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppUserTest {

	AppUser accInp = new AppUser();
	AppUser accInp2 = new AppUser("2","Akash","Akash","xyz","user");

	@Test
	void setAccountIdTest() {
		accInp.setUsername("Akash");
		assertEquals("Akash", accInp.getUsername());
	}

	@Test
	void setAmountTest() {
		accInp.setUserid("emp");
		assertEquals("emp", accInp.getUserid());
	}

	@Test
	public void setPasswoedTest() {
		accInp.setPassword("abc");
		assertEquals("abc", accInp.getPassword());
	}

	@Test
	public void setAuthTokenTest() {
		accInp.setAuthToken("token");
		assertEquals("token", accInp.getAuthToken());
	}

	@Test
	public void setRoleTest() {
		accInp.setRole("user");
		assertEquals("user", accInp.getRole());
	}
	
	@Test
	void getAccIdTest1() {
		assertEquals("2", accInp2.getUserid());
	}

	@Test
	void getRoleTest1() {
		assertEquals("user", accInp2.getRole());
	}

	@Test
	void getUsernameTest1() {
		assertEquals("Akash", accInp2.getUsername());
	}

	@Test
	void getPasswordTest1() {
		assertEquals("Akash", accInp2.getPassword());
	}

	@Test
	void getTokenTest1() {
		assertEquals("xyz", accInp2.getAuthToken());
	}
}