package com.cognizant.accountservice.model;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
	
	private String userid;
	
	private String username;
	
	private String password;
	
	private Date dateOfBirth;
	
	private String pan;
	
	private String address;

}