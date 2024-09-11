package com.cognizant.accountservice.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountErrorResponse {

	private LocalDateTime timestamp;
	
	private HttpStatus status;
	
	private String reason;
	
	private String message;
}
