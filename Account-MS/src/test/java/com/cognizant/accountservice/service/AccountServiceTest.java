package com.cognizant.accountservice.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cognizant.accountservice.exceptionhandling.AccessDeniedException;
import com.cognizant.accountservice.exceptionhandling.AccountNotFoundException;
import com.cognizant.accountservice.exceptionhandling.FeignClientException;
import com.cognizant.accountservice.feignclient.AuthFeignClient;
import com.cognizant.accountservice.feignclient.CustomerFeignProxy;
import com.cognizant.accountservice.feignclient.TransactionFeign;
import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.model.AccountCreationStatus;
import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.AuthenticationResponse;
import com.cognizant.accountservice.repository.AccountRepository;
import com.cognizant.accountservice.service.AccountServiceImpl;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	@Mock
	AuthFeignClient authFeignClient;
	
	@Mock
	CustomerFeignProxy customerFeignProxy;

	@Mock
	AccountRepository accountRepository;

	@Mock
	TransactionFeign transactionFeign;

	@Test
	public void toStringTest() throws ParseException 
	{
		Date date = null;
		date = new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021");
		Account account = new Account(10003, "CUSTOMER101", 1000.0, "Savings", date, "Roshan", null);
		String expected = account.toString();
		assertEquals(expected, account.toString());
	}
	@Test
	void getAccountTestCorrect() throws ParseException {
		Date date = null;
	    date = new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021");
	    Account account = new Account(10003, "CUSTOMER101", 1000.0, "Savings", date, "Roshan", null);
		when(accountRepository.findByAccountId(10003)).thenReturn(account);

		assertEquals("Roshan", accountServiceImpl.getAccount(10003).getOwnerName());
	}

	@Test
	void getAccountTestExceptionMessage() {
		when(accountRepository.findByAccountId(2002)).thenThrow(new AccountNotFoundException("Account Does Not Exist"));
		assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.getAccount(2002));
	}

	@Test
	void getCustomerAccount() throws ParseException {
		Date date = null;
	    date = new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021");
		List<Account> accounts = new ArrayList<>();
		Account acc1 = new Account(101, "CUSTOMER101", 1000.0, "Savings", date,"Krishna", null);
		Account acc2 = new Account(202, "CUSTOMER101", 2000.0, "Current", date,"Mohan", null);

		accounts.add(acc1);
		accounts.add(acc2);

		when(accountRepository.findByCustomerId("CUSTOMER101")).thenReturn(accounts);
//		when(transactionFeign.getTransactionsByAccId("token", 1)).thenReturn(null);
//		when(transactionFeign.getTransactionsByAccId("token", 2)).thenReturn(null);
		assertEquals(2, accountServiceImpl.getCustomerAccount("token", "CUSTOMER101").size());
	}

	@Test
	void createAccount() throws ParseException {
		Date date = null;
			date = new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021");
		Account acc = new Account(1, "CUSTOMER101", 3000.0, "Savings",date, "Krishna", null);
		when(customerFeignProxy.getCustomerDetails("token","CUSTOMER101")).thenReturn(null);
		when(accountRepository.save(acc)).thenReturn(acc);
		AccountCreationStatus status = accountServiceImpl.createAccount("token","CUSTOMER101", acc);
		assertEquals("Sucessfully Created", status.getMessage());
	}

	@Test
	void hasPermissionTest1() {
		when(authFeignClient.tokenValidation("token")).thenReturn(new AuthenticationResponse("CUSTOMER101", "Dhananjay", true));
		assertTrue(accountServiceImpl.hasPermission("token").isValid());
	}

	@Test
	void hasPermissionTest2() {
		when(authFeignClient.tokenValidation("token")).thenThrow(new FeignClientException());
		assertThrows(FeignClientException.class, () -> accountServiceImpl.hasPermission("token"));
	}

	@Test
	void hasCustomerPermissionTest1() {
		when(authFeignClient.tokenValidation("token")).thenReturn(new AuthenticationResponse("CUSTOMER101", "Dhananjay", true));
		when(authFeignClient.getRole("CUSTOMER101")).thenReturn("CUSTOMER");
		assertTrue(accountServiceImpl.hasCustomerPermission("token").isValid());
	}

	@Test
	void hasCustomerPermissionTest2() {
		when(authFeignClient.tokenValidation("token")).thenThrow(new FeignClientException("EXCEPTION"));
		assertThrows(FeignClientException.class, () -> accountServiceImpl.hasCustomerPermission("token"));
	}

	@Test
	void hasEmployeePermissionTest1() {
		when(authFeignClient.tokenValidation("token")).thenReturn(new AuthenticationResponse("EMPLOYEE101", "krishna", true));
		when(authFeignClient.getRole("EMPLOYEE101")).thenReturn("EMPLOYEE");
		assertTrue(accountServiceImpl.hasEmployeePermission("token").isValid());
	}

	@Test
	void hasEmployeePermissionTest2() {
		when(authFeignClient.tokenValidation("token")).thenThrow(new  FeignClientException("EXCEPTION"));
		assertThrows( FeignClientException.class, () -> accountServiceImpl.hasEmployeePermission("token"));
	}


	@Test
	void updateBalanceTest() throws ParseException {
		Date date = null;
			date = new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021");
		Account acc1 = new Account(101, "CUSTOMER101", 1000.0, "Savings", date,"Krishna", null);
		Account acc2 = new Account(101, "CUSTOMER101", 500.0, "Savings", date,"Krishna", null);
		when(accountRepository.findByAccountId(101)).thenReturn(acc1);
		when(accountRepository.save(acc1)).thenReturn(acc2);
		AccountInput ai = new AccountInput(101, 500);
		Account testAccount = accountServiceImpl.updateBalance(ai);
		assertEquals(500, testAccount.getCurrentBalance());
	}

	@Test
	void updateDepositBalanceTest() throws ParseException {
		Date date = null;
			date = new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021");
			Account acc1 = new Account(101, "CUSTOMER101", 1000.0, "Savings", date,"Krishna", null);
			Account acc2 = new Account(101, "CUSTOMER101", 1500.0, "Savings", date,"Krishna", null);
		when(accountRepository.findByAccountId(101)).thenReturn(acc1);
		when(accountRepository.save(acc1)).thenReturn(acc2);
		AccountInput ai = new AccountInput(101, 500);
		Account testAccount = accountServiceImpl.updateDepositBalance(ai);
		assertEquals(1500, testAccount.getCurrentBalance());
	}
}
