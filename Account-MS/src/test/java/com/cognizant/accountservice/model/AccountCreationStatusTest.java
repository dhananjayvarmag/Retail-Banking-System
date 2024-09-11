package com.cognizant.accountservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.accountservice.model.AccountCreationStatus;

public class AccountCreationStatusTest {
	
	AccountCreationStatus ac=new AccountCreationStatus();
	AccountCreationStatus ac1=new AccountCreationStatus(1000000003,"Account Created Successfully");
	
	@Test 
	public void accIdTest()
	{
		ac.setAccountId(1000000003);
		assertEquals(1000000003, ac.getAccountId());
	}
	@Test
	public void messageTest()
	{
		ac.setMessage(null);
		assertEquals(null, ac.getMessage());
	}
	
	@Test 
	public void accIdTest1()
	{
		assertEquals(1000000003, ac1.getAccountId());
	}
	@Test
	public void messageTest1()
	{
		assertEquals("Account Created Successfully", ac1.getMessage());
	}

}
