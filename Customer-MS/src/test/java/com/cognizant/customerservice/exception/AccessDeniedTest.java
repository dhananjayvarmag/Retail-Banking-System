package com.cognizant.customerservice.exception;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.cognizant.customerservice.exception.AccessDeniedException;

public class AccessDeniedTest {
	
	@Test
	public void AccessException() {
		
		AccessDeniedException e1=new AccessDeniedException("hello");
		AccessDeniedException e2=new AccessDeniedException("hello");
		assertNotEquals(e1,e2);
		
	}
	
	@Test
	public void AccessExceptionNull() {
		
		AccessDeniedException e1=new AccessDeniedException();
		AccessDeniedException e2=new AccessDeniedException();
		assertNotEquals(e1,e2);
		
	}
	


}
