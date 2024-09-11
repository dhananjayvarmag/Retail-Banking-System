package com.cognizant.accountservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.accountservice.model.Transaction;

public class TransactionTest {
	
	Transaction tc=new Transaction();
	Transaction transaction2 = new Transaction(10001, 100003, "Krishna", 100004, "Mohan", 1000, null, "transfer");
	
	@Test
	public void setTransactionIdTest()
	{
		tc.setId(1);
		assertEquals(1,tc.getId());
	}
	@Test
	public void setTransactionNameTest()
	{
		tc.setSourceAccountId(1001);
		assertEquals(1001, tc.getSourceAccountId());
	}
	@Test
	public void SetTransactionOwnerTest()
	{
		tc.setSourceOwnerName("Krishna");
		assertEquals("Krishna", tc.getSourceOwnerName());
	}
	
	@Test
	public void setTransactionTargetAccIdTest()
	{
		tc.setTargetAccountId(200315);
		assertEquals(200315, tc.getTargetAccountId());
	}
	
	@Test
	public void setTransactionTOwnerTest()
	{
		tc.setTargetOwnerName("Mohan");
		assertEquals("Mohan", tc.getTargetOwnerName());
	}
	
	@Test
	public void SetAmount()
	{
		tc.setAmount(5000);
		assertEquals(5000, tc.getAmount());
	}
	@Test
	public void SetDateTest()
	{
		tc.setInitiationDate(null);
		assertEquals(null, tc.getInitiationDate());
		
	}
	
	@Test
	public void setReferenceTest() {
		tc.setReference("Deposit");
		assertEquals("Deposit", tc.getReference());
	}
	@Test
	public void setIdTest1() {
		assertEquals(10001, transaction2.getId());
	}

	@Test
	public void setSourceAccountIdTest1() {
		assertEquals(100003, transaction2.getSourceAccountId());
	}

	@Test
	public void setTargetOwnerNameTest1() {
		assertEquals("Mohan", transaction2.getTargetOwnerName());
	}

	@Test
	public void setTargetAccountIdTest1() {
		assertEquals(100004, transaction2.getTargetAccountId());
	}

	@Test
	public void setAmountTest1() {
		assertEquals(1000, transaction2.getAmount());
	}

	@Test
	public void setReferenceTest1() {
		assertEquals("transfer", transaction2.getReference());
	}

	@Test
	public void setInitiationDateTest1() {
		assertEquals(null, transaction2.getInitiationDate());
	}
	@Test
	public void setSourceOwnerTest1()
	{
		
		assertEquals("Krishna", transaction2.getSourceOwnerName());
	}

}
