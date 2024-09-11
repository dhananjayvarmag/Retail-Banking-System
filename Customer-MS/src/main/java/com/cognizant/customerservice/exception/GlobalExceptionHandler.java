package com.cognizant.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cognizant.customerservice.model.ErrorDetails;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerAlreadyExistException.class)
	public ResponseEntity<?> consumerAlreadyExistException(CustomerAlreadyExistException exception, WebRequest request) {
		return new ResponseEntity<>("Customer already exists",  HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> accessDeniedException(AccessDeniedException exception, WebRequest request) {
		return new ResponseEntity<>("Access Denied Exception",  HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> customerNotFoundException(CustomerNotFoundException exception, WebRequest request) {
		return new ResponseEntity<>("Customer Not Found Exception",  HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> occuredException(Exception exception, WebRequest request) {
		return new ResponseEntity<>("Unknown Error Occured",  HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}