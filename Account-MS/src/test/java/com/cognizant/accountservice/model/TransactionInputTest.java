package com.cognizant.accountservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.TransactionInput;

class TransactionInputTest {

	TransactionInput input = new TransactionInput();
	
	AccountInput source = new AccountInput(1, 2000);
	AccountInput destination = new AccountInput(2, 2000);
	TransactionInput input1 = new TransactionInput(source,destination,2000,"fee payment");
	@Test
	void setSourceAccountTest() {
		input.setSourceAccount(source);
		assertEquals(2000, input.getSourceAccount().getAmount());
	}

	@Test
	void setTargetAccountTest() {
		input.setTargetAccount(source);
		assertEquals(1, input.getTargetAccount().getAccountId());
	}

	@Test
	void setAmountTest() {
		input.setAmount(1000);
		assertEquals(1000, input.getAmount());
	}

	@Test
	void setReferenceTest() {
		input.setReference("Withdraw");
		assertEquals("Withdraw", input.getReference());
	}
	
	
	@Test
	void setSourceAccountTest1() {
		assertEquals(2000, input1.getSourceAccount().getAmount());
	}

	@Test
	void setTargetAccountTest1() {
		input.setTargetAccount(destination);
		assertEquals(2, input1.getTargetAccount().getAccountId());
	}

	@Test
	void setAmountTest1() {
		assertEquals(2000, input1.getAmount());
	}

	@Test
	void setReferenceTest1() {
		assertEquals("fee payment", input1.getReference());
	}

}
