package com.rulesservice.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

	@Id
	@NotNull(message = "Enter Account number")
	private long accountId;

	@NotNull(message = "Enter customerId")
	private String customerId;

	@NotNull(message = "Enter currentBalance")
	private double currentBalance;

	@NotNull(message = "Enter accountType")
	private String accountType;

	@NotNull(message = "Enter opening Date")
	private Date openingDate;

	@Column(length = 20)
	@NotNull(message = "Enter ownerName")
	private String ownerName;

	@Transient
	private List<Transaction> transactions;

}