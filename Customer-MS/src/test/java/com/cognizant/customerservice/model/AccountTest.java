package com.cognizant.customerservice.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.model.Account;


class AccountTest {

	Account account = new Account();
	Account account2 = new Account(102, "102", 100.0, "savings",new Date(), "Rao", null);

	@Test
	void setAccountIdTest() {
		account.setAccountId(1);
		assertEquals(1, account.getAccountId());
	}

	@Test
	void setCustomerIdTest() {
		account.setCustomerId("C101");
		assertEquals("C101", account.getCustomerId());
	}

	@Test
	void setCurrentBalanceTest() {
		account.setCurrentBalance(5000);
		assertEquals(5000, account.getCurrentBalance());
	}

	@Test
	void setAccountTypeTest() {
		account.setAccountType("Savings");
		assertEquals("Savings", account.getAccountType());
	}

	@Test
	void setOwnerNameTest() {
		account.setOwnerName("Rao");
		assertEquals("Rao", account.getOwnerName());
	}

	@Test
	void setTransactionsTest() {
		account.setTransactions(null);
		assertEquals(null, account.getTransactions());
	}

	@Test
	void getAccTest() {
		account.setAccountId(1);
		assertEquals(1, account.getAccountId());
	}

	@Test
	void getCustomerTest() {
		account.setCustomerId("C102");
		assertEquals("C102", account.getCustomerId());
	}

	@Test
	void getAcctypeTest() {
		account.setAccountType("Savings");
		assertEquals("Savings", account.getAccountType());
	}

	@Test
	void getTokenTest() {
		account.setCurrentBalance(5000);
		assertEquals(5000, account.getCurrentBalance());
	}
	
	@Test
	void getOwnerTest() {
		account.setOwnerName("Rao");
		assertEquals("Rao", account.getOwnerName());
	}

}
