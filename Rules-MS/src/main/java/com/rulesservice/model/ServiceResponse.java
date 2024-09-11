package com.rulesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ServiceResponse {
	private String message;
	private long accountId;
	private Double balance;

}
