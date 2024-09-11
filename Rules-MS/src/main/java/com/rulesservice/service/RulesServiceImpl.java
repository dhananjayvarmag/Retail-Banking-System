package com.rulesservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rulesservice.exception.AccessDeniedException;
import com.rulesservice.feign.AccountFeign;
import com.rulesservice.feign.AuthorizationFeign;
import com.rulesservice.model.Account;
import com.rulesservice.model.AccountInput;
import com.rulesservice.model.AuthenticationResponse;
import com.rulesservice.model.RulesInput;

@Service
public class RulesServiceImpl implements RulesService {

	@Autowired
	AuthorizationFeign authorizationFeign;
	@Autowired
	AccountFeign accountFeign;

	@Override
	public boolean evaluate(RulesInput account) {
		int min = 1000;
		double check = account.getCurrentBalance() - account.getAmount();
		if (check >= min)
			return true;
		else
			return false;
	}

	@Override
	public AuthenticationResponse hasPermission(String token) {
		AuthenticationResponse validity = authorizationFeign.getValidity(token);
		if (!authorizationFeign.getRole(validity.getUserid()).equals("EMPLOYEE"))
			throw new AccessDeniedException("NOT ALLOWED");
		else
			return validity;
	}

	@Override
	public double serviceCharges(Account account) {
		double detected = account.getCurrentBalance() / 10;
		if (account.getCurrentBalance() < 2000 && (account.getCurrentBalance() - detected) > 0) {
			return detected;
		} 
		return 0.0;
	}
	
	@Override
	public List<Account> applyServiceCharges(String token)
	{
		try {
			List<Account> body = accountFeign.getAllAccount(token).getBody();
			for (Account acc : body) {
				if (serviceCharges(acc) > 0) {
					accountFeign.servicecharge(token,
							new AccountInput(acc.getAccountId(), serviceCharges(acc)));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		List<Account> list = accountFeign.getAllAccount(token).getBody();
		return list;
	}

}
