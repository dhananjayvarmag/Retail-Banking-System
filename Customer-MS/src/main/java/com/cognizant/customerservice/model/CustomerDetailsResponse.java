package com.cognizant.customerservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDetailsResponse {

	private String userid;

	private String username;

	private String password;

	private Date dateOfBirth;

	private String pan;

	private String address;

	private List<Account> accounts = new ArrayList<>();

}
