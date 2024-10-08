package com.cognizant.customerservice.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.customerservice.model.AuthenticationResponse;
import com.cognizant.customerservice.CustomerServiceApplication;
import com.cognizant.customerservice.model.AppUser;
import com.cognizant.customerservice.model.CustomerEntity;
import com.cognizant.customerservice.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CustomerServiceApplication.class })
public class CustomerTests {

	public String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFTVBMT1lFRTEwMSIsImV4cCI6MTYwODU3MDk1MSwiaWF0IjoxNjA4MzU0OTUxfQ.CLuewsfeFIYwVIGftqkMGhvuEf4PqP4Fl8TKKIifNtw";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;
	@MockBean
	private CustomerService customerService;

	List<AppUser> employees = new ArrayList<AppUser>();
	static ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() throws JsonProcessingException, Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}

	@Test
	public void createCustomer() throws JsonProcessingException, Exception {
		CustomerEntity ce = new CustomerEntity();
		ce.setAddress("Hyderabad");
		ce.setDateOfBirth(new Date(60));
		ce.setPan("ABCFE1234S");
		ce.setPassword("Asritha");
		ce.setUsername("Asritha");
		ce.setUserid("CUSTOMER101");
		String json = MAPPER.writeValueAsString(ce);
		when(customerService.hasEmployeePermission(token)).thenReturn(null);
		when( customerService.createCustomer(token, ce)).thenReturn(ce);
		@SuppressWarnings("unused")
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/createCustomer").header("Authorization", token)
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(201)).andReturn();

	}

	@Test
	public void getCustomersSuccess() throws JsonProcessingException, Exception {
		System.err.println(token);
		CustomerEntity ce = new CustomerEntity();
		ce.setAddress("Hyderabad");
		ce.setDateOfBirth(new Date(60));
		ce.setPan("ABCFE1234S");
		ce.setPassword("Asritha");
		ce.setUsername("Asritha");
		ce.setUserid("CUSTOMER101");
		when(customerService.hasPermission("token"))
				.thenReturn(new AuthenticationResponse("CUSTOMER101", "cust", true));
		when(customerService.getCustomerDetail("token", "CUSTOMER101")).thenReturn(ce);
		mockMvc.perform(get("/getCustomerDetails/CUSTOMER101").header("Authorization", "token"))
				.andExpect(status().isOk());
	}



}