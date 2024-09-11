package com.cognizant.transactionservice.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.transactionservice.exception.MinimumBalanceException;
import com.cognizant.transactionservice.models.AccountInput1;
import com.cognizant.transactionservice.models.Transaction;
import com.cognizant.transactionservice.models.TransactionInput;
import com.cognizant.transactionservice.repository.TransactionRepository;
import com.cognizant.transactionservice.service.TransactionService;
import com.cognizant.transactionservice.feign.AccountFeign;
import com.cognizant.transactionservice.feign.RulesFeign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionRestControllerTest {
    
	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;

	static ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() throws JsonProcessingException, Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}
	
	@MockBean
	TransactionRepository transRepo;
	
	@MockBean
	AccountFeign accountFeign;
	
	@MockBean
	RulesFeign rulesFeign;

	@MockBean
	TransactionService transactionService;
	String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFTVBMT1lFRTEwMSIsImV4cCI6MTYxNjQxODk0OCwiaWF0IjoxNjE2NDE3MTQ4fQ.ln-8ceskNQJuKQWPygb9bbM89fiJ-lJphoem2-_Eulk";


	@Test
	public void performTransactionTest() throws Exception {
		TransactionInput trans=new TransactionInput();
		trans.setSourceAccount(new AccountInput1(1000000001,1000.0));
		trans.setTargetAccount(new AccountInput1(1000000001,1000.0));
		trans.setAmount(1000.0);
		trans.setReference("TRANSFER");
		MvcResult andReturn = mockMvc.perform(post("/transactions")
				.content(asJsonString(trans))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
				.andExpect(status().isOk()).andReturn();
		String contentAsString = andReturn.getResponse().getContentAsString();
	}
	
	@Test
	public void withdraw() throws Exception {
		AccountInput1 trans=new AccountInput1(1000000001, 100.0);
		 MvcResult andReturn = mockMvc.perform(post("/withdraw")
				.content(asJsonString(trans))
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
				.andExpect(status().isOk()).andReturn();
		 String contentAsString = andReturn.getResponse().getContentAsString();
		 System.err.println(contentAsString);
	}
	
	@Test
	public void servicecharge() throws Exception {
		AccountInput1 trans=new AccountInput1(1000000001, 100.0);
		 MvcResult andReturn = mockMvc.perform(post("/servicecharge")
				.content(asJsonString(trans))
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
				.andExpect(status().isOk()).andReturn();
		 String contentAsString = andReturn.getResponse().getContentAsString();
		 System.err.println(contentAsString);
	}
	
	@Test
	public void deposit() throws Exception{
		AccountInput1 trans = new AccountInput1(1000000001, 100.0);
		MvcResult andReturn = mockMvc.perform(post("/deposit")
				.content(asJsonString(trans))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
				.andExpect(status().isOk()).andReturn();
		String contentAsString = andReturn.getResponse().getContentAsString();
	}
	@Test
	public void getAllTransactionById() throws Exception {
		Transaction trans = new Transaction();
		MvcResult andReturn = mockMvc.perform(get("/getAllTransByAccId/1000000001").header("Authorization", token)).andExpect(status().isOk()).andDo(print()).andReturn();
		
	}
	
	
	@Test
	public void minimumBal() throws MinimumBalanceException,Exception {
		MinimumBalanceException minimumBalanceException = new MinimumBalanceException("Minimum Balance should be 500");
		MinimumBalanceException minimumBalanceException2 = new MinimumBalanceException("Minimum Balance should be 1000");
		assertNotEquals(minimumBalanceException, minimumBalanceException2);		
	}
		
	public static String asJsonString(final Object obj) throws JsonProcessingException {
		
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		
	}
	
}