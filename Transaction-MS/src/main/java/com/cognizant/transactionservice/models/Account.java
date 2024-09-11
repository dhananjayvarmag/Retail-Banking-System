package com.cognizant.transactionservice.models;

import java.util.Date;
import java.util.List;


import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	private long accountId;

	private String customerId;

	private double currentBalance;

	private String accountType;
	
	private Date openingDate;

	private String ownerName;

	@Transient
	private transient List<Transaction> transactions;

}