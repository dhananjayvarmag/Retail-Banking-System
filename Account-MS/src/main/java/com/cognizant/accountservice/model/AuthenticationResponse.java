package com.cognizant.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	
	
	private String userid;
	
	private String name;
	
	private boolean isValid;
}