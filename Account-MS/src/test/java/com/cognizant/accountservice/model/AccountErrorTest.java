package com.cognizant.accountservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.cognizant.accountservice.model.AccountErrorResponse;

public class AccountErrorTest {
	
	AccountErrorResponse re=new AccountErrorResponse();
	AccountErrorResponse re1=new AccountErrorResponse(LocalDateTime.now(),HttpStatus.NOT_ACCEPTABLE,"Customer does not exist","Customer does not exist");

	@Test
	public void dateTest()
	{
		re.setTimestamp(null);
		assertEquals(null, re.getTimestamp());
	}
	@Test
	public void statusTest()
	{
		re.setStatus(null);
		assertEquals(null, re.getStatus());
	}
	@Test
	public void reasonTest()
	{
		re.setReason("Customer does not exist");
		assertEquals("Customer does not exist", re.getReason());
	}
	@Test
	public void messageTest()
	{
		re.setMessage("Customer does not exist");
		assertEquals("Customer does not exist", re.getMessage());
	}
	
	@Test
	public void statusTest1()
	{

		assertEquals(HttpStatus.NOT_ACCEPTABLE, re1.getStatus());
	}
	@Test
	public void reasonTest1()
	{
		assertEquals("Customer does not exist", re1.getReason());
	}
	@Test
	public void messageTest1()
	{

		assertEquals("Customer does not exist", re1.getMessage());
	}
}
