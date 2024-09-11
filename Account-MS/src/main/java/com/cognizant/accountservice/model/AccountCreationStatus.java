package com.cognizant.accountservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountCreationStatus {

	private long accountId;
	
	private String message;
}
