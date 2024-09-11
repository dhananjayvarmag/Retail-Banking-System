package com.cognizant.accountservice.exceptionhandling;

public class FeignClientException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public FeignClientException() {
		super();
	}

	public FeignClientException(String message) {
		super(message);
	}

}
