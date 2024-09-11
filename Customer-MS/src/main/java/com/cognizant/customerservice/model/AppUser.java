package com.cognizant.customerservice.model;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUser {

	@Id
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	@Column(name = "userid", length = 20)
	private String userid;

	@Pattern(regexp = "^[A-Za-z0-9]*$")
	@Column(name = "username", length = 20)
	private String username;

	@Column(name = "password")
	private String password;

	private String authToken;

	private String role;
}
