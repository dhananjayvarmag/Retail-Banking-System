package com.cognizant.accountservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInput {
	
	@NotNull(message = "{account.id.not.null}")
	private long accountId;

	@Min(value=1, message="{amount.not.enough}")
	private double amount;

}