package com.cognizant.accountservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.accountservice.model.AuthenticationResponse;

public class AuthenticationResponseTest {
	
	AuthenticationResponse ae=new AuthenticationResponse();
	AuthenticationResponse ae1=new AuthenticationResponse("EMPLOYEE101","employee 101",true);
	@Test
	public void userIdTest()
	{
		ae.setUserid("EMPLOYEE102");
		assertEquals("EMPLOYEE102", ae.getUserid());
	}
	@Test
	public void nameTest()
	{
		ae.setName("employee 102");
		assertEquals("employee 102", ae.getName());
	}
	@Test
	public void isValidTest()
	{
		ae.setValid(true);
		assertEquals(true, ae.isValid());
	}
	
	@Test
	public void userIdTest1()
	{
		assertEquals("EMPLOYEE101", ae1.getUserid());
	}
	@Test
	public void nameTest1()
	{
		assertEquals("employee 101", ae1.getName());
	}
	@Test
	public void isValidTest1()
	{
		assertEquals(true, ae1.isValid());
	}
	
	@Test
	public void toStringTest()
	{
		String expected = ae.toString();
		assertEquals(expected, ae.toString());
	}

}
