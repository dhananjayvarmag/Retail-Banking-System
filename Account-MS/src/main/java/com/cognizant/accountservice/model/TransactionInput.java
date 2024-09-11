package com.cognizant.accountservice.model;

import javax.validation.constraints.Min;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInput {
	
	private AccountInput sourceAccount;
	private AccountInput targetAccount;
	
	@Min(value = 1, message = "{amount.not.enough}")
	private double amount;
	private String reference;

}