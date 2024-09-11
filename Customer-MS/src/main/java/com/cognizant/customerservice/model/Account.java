package com.cognizant.customerservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

	private long accountId;

	private String customerId;

	private double currentBalance;

	private String accountType;

	private Date openingDate;

	private String ownerName;

	@Transient
	private List<Transaction> transactions = new ArrayList<>();

}