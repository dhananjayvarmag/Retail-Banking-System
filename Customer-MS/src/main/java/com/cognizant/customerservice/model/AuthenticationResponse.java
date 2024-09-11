package com.cognizant.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	
	public AuthenticationResponse(boolean isValid) {
		super();
		this.isValid = isValid;
	}
	
	private String userid;
	
	private String name;
	
	private boolean isValid;
	
}