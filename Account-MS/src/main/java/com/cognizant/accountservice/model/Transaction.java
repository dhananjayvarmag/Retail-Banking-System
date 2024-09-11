package com.cognizant.accountservice.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	private long id;

	private long sourceAccountId;
	
	private String sourceOwnerName;
	
	private long targetAccountId;
	
	private String targetOwnerName;
	
	private double amount;
	
	private LocalDateTime initiationDate;
	
	private String reference;

}