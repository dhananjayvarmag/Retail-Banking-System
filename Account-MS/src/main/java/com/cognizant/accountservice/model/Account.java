package com.cognizant.accountservice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;


@Entity
@Table(name = "ACCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor 

public class Account {

	@Id
	@Column(length=10)
	@SequenceGenerator(name="seq", initialValue = 1000000003)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
	private long accountId;
	
	@NotBlank(message = "{customer.id.not.blank}")
	private String customerId;

	@Min(value=500, message="{customer.balance.min.value}") 
	private double currentBalance;

	@NotBlank(message = "{account.type.not.blank}")
	private String accountType;

	@NotNull(message = "{opening.date.not.blank}")
	private Date openingDate;

	
	
	@Column(length = 20)
	@NotBlank(message = "{owner.name.not.blank}")
	private String ownerName;


	@Transient
	private List<Transaction> transactions;
	
	@Override
	public String toString() {
		return "Account information : [accountId=" + accountId + ", openingDate=" + openingDate + ", currentBalance=" + currentBalance
				+ ", accountType=" + accountType + "]";
	}

	public Account(@NotBlank(message = "{customer.id.not.blank}") String customerId,
			@Min(value = 500, message = "{customer.balance.min.value}") double currentBalance,
			@NotBlank(message = "{account.type.not.blank}") String accountType,
			@NotNull(message = "{opening.date.not.blank}") Date openingDate,
			@NotBlank(message = "{owner.name.not.blank}") String ownerName, List<Transaction> transactions) {
		super();
		this.customerId = customerId;
		this.currentBalance = currentBalance;
		this.accountType = accountType;
		this.openingDate = openingDate;
		this.ownerName = ownerName;
		this.transactions = transactions;
	}
	

}