package com.cognizant.transactionservice.models;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RulesInput {	

	@NotNull
	private long accountId;
	@NotNull
	private double currentBalance;
	@NotNull
	private double amount;

}
