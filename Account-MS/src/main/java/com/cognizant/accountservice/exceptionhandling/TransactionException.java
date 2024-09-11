package com.cognizant.accountservice.exceptionhandling;

public class TransactionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public TransactionException() {
		super();
	}

	public TransactionException(String message) {
		super(message);
	}

}
