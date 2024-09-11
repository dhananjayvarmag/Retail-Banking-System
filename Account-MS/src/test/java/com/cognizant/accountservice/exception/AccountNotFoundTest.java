package com.cognizant.accountservice.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.cognizant.accountservice.exceptionhandling.AccountNotFoundException;

public class AccountNotFoundTest {
	
	@Test
	public void accountExceptionTest() {
		
		AccountNotFoundException e1=new AccountNotFoundException("Account Not Found Exception");
		AccountNotFoundException e2=new AccountNotFoundException("Account Not Found Exception");
		assertThat(e1).isNotEqualTo(e2);
		
	}
	
	@Test
	public void accountExceptionNullTest() {
		
		AccountNotFoundException e1=new AccountNotFoundException();
		AccountNotFoundException e2=new AccountNotFoundException();
		assertThat(e1).isNotEqualTo(e2);
		
	}

}
