package com.cognizant.accountservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.accountservice.exceptionhandling.AccessDeniedException;
import com.cognizant.accountservice.exceptionhandling.AccountNotFoundException;
import com.cognizant.accountservice.exceptionhandling.FeignClientException;
import com.cognizant.accountservice.exceptionhandling.MinimumBalanceException;
import com.cognizant.accountservice.exceptionhandling.TransactionException;
import com.cognizant.accountservice.feignclient.AuthFeignClient;
import com.cognizant.accountservice.feignclient.CustomerFeignProxy;
import com.cognizant.accountservice.feignclient.TransactionFeign;
import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.model.AccountCreationStatus;
import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.AuthenticationResponse;
import com.cognizant.accountservice.model.MessageDetails;
import com.cognizant.accountservice.model.Statement;
import com.cognizant.accountservice.model.Transaction;
import com.cognizant.accountservice.model.TransactionInput;
import com.cognizant.accountservice.repository.AccountRepository;
import com.cognizant.accountservice.repository.StatementRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	/*
	 * Autowiring the FeignClient Services to communicate with other microservices
	 */
	@Autowired
	private AuthFeignClient authFeignClient;
	@Autowired
	private TransactionFeign transactionFeign;
	@Autowired
	private CustomerFeignProxy customerFeignProxy;

	/*
	 * Autowiring the repositories
	 */
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private StatementRepository statementRepository;

	// creating new account and storing it in the database
	
	@Override
	public AccountCreationStatus createAccount(String token,String customerId, Account account) {
		try {
		customerFeignProxy.getCustomerDetails(token, customerId);
		}
		catch(Exception e)
		{
			throw new FeignClientException();
		}
		accountRepository.save(account);
		AccountCreationStatus accountCreationStatus = new AccountCreationStatus(account.getAccountId(), "Sucessfully Created");
		LOGGER.info("Account Created Successfully");
		return accountCreationStatus;
		
	}

	// getting the account details based on the customer
	
	@Override
	public List<Account> getCustomerAccount(String token, String customerId) {
		List<Account> accountList = accountRepository.findByCustomerId(customerId);
		for (Account acc : accountList) {
			try {
			acc.setTransactions(transactionFeign.getTransactionsByAccId(token, acc.getAccountId()));
			}
			catch(Exception e)
			{
				throw new FeignClientException();
			}
		}
		return accountList;
	}

	// Getting the account details for the given account id

	@Override
	public Account getAccount(long accountId) {
		Account account = accountRepository.findByAccountId(accountId);
		if (account == null) {
			LOGGER.error("");
			throw new AccountNotFoundException();
		}
		return account;
	}

	// updating the current balance during withdraw, service charge deduction,
	// transfer
	
	@Override
	public Account updateBalance(AccountInput accountInput) {
		LOGGER.info("Account to update " + accountInput.getAccountId());
		Account toUpdateAcc = accountRepository.findByAccountId(accountInput.getAccountId());
		toUpdateAcc.setCurrentBalance(toUpdateAcc.getCurrentBalance() - accountInput.getAmount());
		return accountRepository.save(toUpdateAcc);
	}

	// updating the current balance during deposit and transfer

	@Override
	public Account updateDepositBalance(AccountInput accountInput) {
		LOGGER.info("Account to update " + accountInput.getAccountId());
		Account toUpdateAcc = accountRepository.findByAccountId(accountInput.getAccountId());
		if(toUpdateAcc == null)
			throw new AccountNotFoundException("Account is not found");
		toUpdateAcc.setCurrentBalance(toUpdateAcc.getCurrentBalance() + accountInput.getAmount());
		return accountRepository.save(toUpdateAcc);
	}

	// Validating the token using authorization microservice
	
	@Override
	public AuthenticationResponse hasPermission(String token) {
		try {
		return authFeignClient.tokenValidation(token);
		}
		catch(Exception e)
		{
			throw new FeignClientException();
		}
	}


	// Checking whether the user has employee permission or not
	@Override
	public AuthenticationResponse hasEmployeePermission(String token) {
		AuthenticationResponse validity;
		try {
		validity = authFeignClient.tokenValidation(token);
		}
		catch(Exception e)
		{
			throw new FeignClientException();
		}
		if (!authFeignClient.getRole(validity.getUserid()).equalsIgnoreCase("EMPLOYEE")) {
			throw new AccessDeniedException();
		}
		return validity;
	}

	// Checking whether the user has customer permission or not
	@Override
	public AuthenticationResponse hasCustomerPermission(String token) {
		AuthenticationResponse validity;
		try {
		validity = authFeignClient.tokenValidation(token);
		}
		catch(Exception e)
		{
			throw new FeignClientException();
		}
		if (!authFeignClient.getRole(validity.getUserid()).equalsIgnoreCase("CUSTOMER")) {
			throw new AccessDeniedException();
		}
		return validity;
	}

	// Getting all the account details from the database
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts;
	}

	

	// Updating the account statement after withdrawal, deposit and service charge
	// deduction

	@Override
	public void updateStatement(AccountInput accInput, Account newUpdateAccBal, String message) {
		long accountId = accInput.getAccountId();
		Statement statement = new Statement(accountId, 0, accInput.getAmount(),
				newUpdateAccBal.getCurrentBalance(), 0, new Date(), message);
		statementRepository.save(statement);
	}

	// Updating the account statement after transaction

	@Override
	public void updateStatement(Account updatedSourceAccBal, Account updatedTargetAccBal, double amount,
			String message) {
		Statement statement = new Statement(updatedSourceAccBal.getAccountId(), updatedTargetAccBal.getAccountId(),
				amount, updatedSourceAccBal.getCurrentBalance(), updatedTargetAccBal.getCurrentBalance(), new Date(),
				message);
		statementRepository.save(statement);

	}

	// Getting the account statements for the last 30 days
	@Override
	public List<Statement> getAccountStatement(long accountId) {
		Date startDate = new Date();
		LocalDateTime date = LocalDateTime.now().minusDays(30);
		Date endDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
		List<Statement> statements = statementRepository.findStatementByAccountId(accountId, endDate, startDate);
		return statements;
	}

	// Getting the account statements between the given dates

	@Override
	public List<Statement> getAccountStatement(long accountId, String from, String to) throws ParseException {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c = Calendar.getInstance();
	    c.setTime(formatDate.parse(to));
	    c.add(Calendar.DATE, 1);
	    to = formatDate.format(c.getTime());
		
		Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
		Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
		List<Statement> statements = statementRepository.findStatementByAccountId(accountId, fromDate, toDate);
		return statements;
	}
	
	//depositing the amount to user account
	
	@Override
	public Account depositAmount(String token, AccountInput accInput)
	{
		Account newUpdateAccBal;
		try {
		transactionFeign.makeDeposit(token, accInput);
		//Updating the new current balance after deposit
		newUpdateAccBal = updateDepositBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		updateStatement(accInput,newUpdateAccBal,"Deposited");
		LOGGER.info("Amount Deposited");
		}
         catch(Exception e)
          {
	            throw new FeignClientException();
           }
		return newUpdateAccBal;
	}
	
	//withdraw amount from the user account
    public Account withdrawAmount(String token, AccountInput accInput)
    {
    	try {
			transactionFeign.makeWithdraw(token, accInput);

		} catch (Exception e) {
			LOGGER.error("Minimum Balance 1000 should be maintaind");
			throw new MinimumBalanceException();
		}
		//Updating the new current balance after withdrawal
		Account newUpdateAccBal = updateBalance(accInput);
		List<Transaction> list;
		try {
		list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		}
		catch(Exception e)
		{
			throw new FeignClientException();
		}
		newUpdateAccBal.setTransactions(list);
		updateStatement(accInput,newUpdateAccBal,"Withdrawn");
		LOGGER.info("Amount withdrawn successfully");
		return newUpdateAccBal;
    }
    
    //Applying service charges for the accounts
    
    public Account servicecharge(String token, AccountInput accInput)
    {
    	try {
    	transactionFeign.makeServiceCharges(token, accInput);
    	}
    	catch(Exception e)
    	{
    		throw new FeignClientException();
    	}
		//Updating the new current balance after service charge deduction
		Account newUpdateAccBal = updateBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		updateStatement(accInput,newUpdateAccBal,"Service charge");
		LOGGER.info("Service charge deducted successfully");
		return newUpdateAccBal;
    }
    
    //Transferring money from one account to the other
    
    public MessageDetails makeTransfer(String token, TransactionInput transInput)
    {
    	MessageDetails messageDetails=new MessageDetails();
    	boolean status = true;
		try {
			status = transactionFeign.makeTransfer(token, transInput);

		} catch (Exception e) {
			LOGGER.error("Minimum Balance 1000 should be maintaind");
			throw new MinimumBalanceException("Minimum Balance 1000 should be maintaind");
		}
		if (status == false) {
			throw new TransactionException();
		}
		//Updating the source account
		Account updatedSourceAccBal = updateBalance(transInput.getSourceAccount());
		List<Transaction> sourceAcc = transactionFeign.getTransactionsByAccId(token,transInput.getSourceAccount().getAccountId());
		updatedSourceAccBal.setTransactions(sourceAcc);
		
		//Updating the target account
		Account updatedTargetAccBal = updateDepositBalance(transInput.getTargetAccount());
		List<Transaction> targetAcc = transactionFeign.getTransactionsByAccId(token,transInput.getTargetAccount().getAccountId());
		updatedTargetAccBal.setTransactions(targetAcc);
		
		//Updating the account statement
		updateStatement(updatedSourceAccBal,updatedTargetAccBal,transInput.getAmount(),"Transferred");
		LOGGER.info("Transaction completed successfully from Account " + transInput.getSourceAccount().getAccountId()+ " to Target Account " + transInput.getTargetAccount().getAccountId());
		messageDetails.setMessage("Transaction Successfully Done..");
		
		return messageDetails;
    }
}
