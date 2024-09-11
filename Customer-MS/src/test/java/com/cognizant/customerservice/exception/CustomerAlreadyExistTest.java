package com.cognizant.customerservice.exception;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.cognizant.customerservice.exception.CustomerAlreadyExistException;

public class CustomerAlreadyExistTest {
	
	
	@Test
	public void customerAlreadyExist() {
		CustomerAlreadyExistException e1 = new CustomerAlreadyExistException("hi");
		CustomerAlreadyExistException e2 = new CustomerAlreadyExistException("hi");
		assertNotEquals(e1,e2);
	}
	@Test
	public void customerAlreadyExist2() {
		CustomerAlreadyExistException e1 = new CustomerAlreadyExistException();
		CustomerAlreadyExistException e2 = new CustomerAlreadyExistException();
		assertNotEquals(e1,e2);
	}
	
	

}
