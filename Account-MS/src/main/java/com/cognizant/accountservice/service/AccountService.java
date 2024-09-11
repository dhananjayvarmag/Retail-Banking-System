package com.cognizant.accountservice.service;

import java.text.ParseException;
import java.util.List;

import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.model.AccountCreationStatus;
import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.AuthenticationResponse;
import com.cognizant.accountservice.model.MessageDetails;
import com.cognizant.accountservice.model.Statement;
import com.cognizant.accountservice.model.TransactionInput;

public interface AccountService {

	public AccountCreationStatus createAccount(String token,String customerId, Account account);

	public List<Account> getCustomerAccount(String token, String customerId);

	public Account getAccount(long accountId);

	public AuthenticationResponse hasPermission(String token);

	public AuthenticationResponse hasEmployeePermission(String token);

	public AuthenticationResponse hasCustomerPermission(String token);

	public Account updateDepositBalance(AccountInput accountInput);

	public Account updateBalance(AccountInput accountInput);

	public List<Account> getAllAccounts();

	List<Statement> getAccountStatement(long accountId);

	List<Statement> getAccountStatement(long accountId, String from, String to) throws ParseException;

	void updateStatement(Account updatedSourceAccBal, Account updatedTargetAccBal, double amount, String message);

	void updateStatement(AccountInput accInput, Account newUpdateAccBal, String message);
	
	public Account depositAmount(String token, AccountInput accInput);
	
	public Account withdrawAmount(String token, AccountInput accInput);
	
	public Account servicecharge(String token, AccountInput accInput);
	
	 public MessageDetails makeTransfer(String token, TransactionInput transInput);
	

}
