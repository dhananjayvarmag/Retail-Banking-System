package com.cognizant.accountservice.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.cognizant.accountservice.exceptionhandling.MinimumBalanceException;

public class MinimumBalanceTest {
	
	@Test
	public void minimumExceptionTest() {
		
		MinimumBalanceException e1=new  MinimumBalanceException("Minimum Balance must be 1000");
		MinimumBalanceException e2=new  MinimumBalanceException("Minimum Balance must be 1000");
		assertThat(e1).isNotEqualTo(e2);
		
	}
	
	@Test
	public void minimumExceptionNullTest() {
		
		MinimumBalanceException e1=new  MinimumBalanceException();
		MinimumBalanceException e2=new  MinimumBalanceException();
		assertThat(e1).isNotEqualTo(e2);
		
	}

}
