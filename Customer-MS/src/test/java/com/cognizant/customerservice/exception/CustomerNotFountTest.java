package com.cognizant.customerservice.exception;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.exception.CustomerNotFoundException;

public class CustomerNotFountTest {
	

	@Test
	void customerTest()
	{
		CustomerNotFoundException l1=new CustomerNotFoundException("Nani");
		CustomerNotFoundException l2=new CustomerNotFoundException("Nani");
		assertNotEquals(l1,l2);
	}
	
	@Test
	void customerTest2()
	{
		CustomerNotFoundException l1=new CustomerNotFoundException();
		CustomerNotFoundException l2=new CustomerNotFoundException();
		assertNotEquals(l1,l2);
	}
	

}
