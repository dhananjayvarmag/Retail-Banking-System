package com.cognizant.accountservice.controller;


import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 
import com.cognizant.accountservice.feignclient.AuthFeignClient;
import com.cognizant.accountservice.feignclient.TransactionFeign;
import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.model.AccountCreationStatus;
import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.AuthenticationResponse;
import com.cognizant.accountservice.repository.AccountRepository;
import com.cognizant.accountservice.service.AccountServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private AccountServiceImpl accountServiceImpl;
 
    @MockBean
    private AccountRepository accountRepository;
 
    @MockBean
    private TransactionFeign transactionFeign;
 
    //Test method to test the /getAccount/{accountId} end point
    @Test
    void getAccountTest() throws Exception {
        when(accountServiceImpl.hasPermission("token")).thenReturn(new AuthenticationResponse("EMPLOYEE101", "", true));
        Account acc = new Account();
        when(accountServiceImpl.getAccount(1000000003)).thenReturn(acc);
        mockMvc.perform(get("/getAccount/1000000003").header("Authorization", "token")).andExpect(status().isOk());
        verify(accountServiceImpl, times(1)).getAccount(1000000003);
        verify(accountServiceImpl, times(1)).hasPermission("token");
    }
 
    
    //Testing method to test the /getAccounts/{customerId} end point
    @Test
    void getCustomerAccountTest() throws Exception {
        when(accountServiceImpl.hasPermission("token")).thenReturn(new AuthenticationResponse("EMPLOYEE101", "", true));
        List<Account> obj = new ArrayList<Account>();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2022");
        obj.add(new Account("CUSTOMER101", 3000.0, "Savings", date, "Nike Showroom", null));
        when(accountServiceImpl.getCustomerAccount("token", "CUSTOMER101")).thenReturn(obj);
        mockMvc.perform(get("/getAccounts/CUSTOMER101").header("Authorization", "token")).andExpect(status().isOk()).andDo(print());
        verify(accountServiceImpl, times(1)).getCustomerAccount("token", "CUSTOMER101");
    }
 
    //Test method to test the /createAccount/{customerId} end point
    @Test
    void createAccountTest() throws Exception {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2022");
        when(accountServiceImpl.hasEmployeePermission("token")).thenReturn(new AuthenticationResponse("EMPLOYEE101", "", true));
        Account account = new Account("CUSTOMER101", 3000.0, "Savings", date, "Nike Showroom", null);
        when(accountServiceImpl.createAccount("token","CUSTOMER101", account)).thenReturn(new AccountCreationStatus(1, "Sucessfully Created"));
        mockMvc.perform(MockMvcRequestBuilders
        .post("/createAccount/CUSTOMER101")
        .content(asJsonString(account))
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .header("Authorization", "token")).andExpect(status().is(201));
        verify(accountServiceImpl, times(1)).hasEmployeePermission("token");
        verify(accountServiceImpl, times(1)).createAccount("token","CUSTOMER101", account);
    } 


    //Testing method for /find end point
    @Test
    void  getAllAccountTest() throws Exception  {
        when(accountServiceImpl.hasPermission("token")).thenReturn(new AuthenticationResponse("EMPLOYEE101", "", true));
        when(accountServiceImpl.getAllAccounts()).thenReturn(new ArrayList<Account>());
        mockMvc.perform(get("/find").header("Authorization", "token")).andExpect(status().isOk());
        verify(accountServiceImpl, times(1)).getAllAccounts();
    }
 
    //Converts object into an json format string
    public static String asJsonString(final Object obj) throws JsonProcessingException {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
 
    }
}