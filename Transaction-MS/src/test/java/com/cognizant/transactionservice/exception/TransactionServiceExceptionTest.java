package com.cognizant.transactionservice.exception;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionServiceExceptionTest {
	
	MinimumBalanceException minimumBalanceException=new MinimumBalanceException("Message 1");
	MinimumBalanceException minimumBalanceException1=new MinimumBalanceException("Message 2");

	@Test
	public void testminimumBalance() {
		
		assertNotEquals(minimumBalanceException, minimumBalanceException1);
	}

}
