package com.cognizant.accountservice.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cognizant.accountservice.model.AccountErrorResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MinimumBalanceException.class)
	public ResponseEntity<?> minimumBalanceException(MinimumBalanceException exception,
			WebRequest request) {
		return new ResponseEntity<>("Minimum Balance Exception", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> accountNotFoundException(AccountNotFoundException exception,
			WebRequest request) {
		return new ResponseEntity<>("Account Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> accessDeniedException(AccessDeniedException exception,
			WebRequest request) {
		return new ResponseEntity<>("Access Denied", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(FeignClientException.class)
	public ResponseEntity<?> feignClientException(FeignClientException exception,
			WebRequest request) {
		return new ResponseEntity<>("Feign Client Exception", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<?> transactionException(TransactionException exception,
			WebRequest request) {
		return new ResponseEntity<>("Transaction not successful", HttpStatus.NOT_IMPLEMENTED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalException(Exception exception, WebRequest request) {
		return new ResponseEntity<>("Unknown Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
