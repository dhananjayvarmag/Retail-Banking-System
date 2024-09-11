package com.cognizant.transactionservice.exception;

public class FeignClientNotReachableException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public FeignClientNotReachableException(String message) {
		super(message);
	}

}
