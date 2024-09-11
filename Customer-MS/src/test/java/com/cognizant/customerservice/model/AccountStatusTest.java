package com.cognizant.customerservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.model.AccountCreationStatus;


class AccountStatusTest {
	AccountCreationStatus account = new AccountCreationStatus();
	AccountCreationStatus account2 = new AccountCreationStatus(101,"Welcome");
	

	@Test
	void setAccTest() {
		account.setAccountId(1);
		assertEquals(1, account.getAccountId());
	}

	@Test
	void setMsgTest() {
		account.setMessage("message");
		assertEquals("message", account.getMessage());
	}

	@Test
	void getMessageTest() {
		account.setMessage("message");
		assertEquals("message", account.getMessage());
	}

	@Test
	void getAccTest() {
		account.setAccountId(1);
		assertEquals(1, account.getAccountId());
	}

}
