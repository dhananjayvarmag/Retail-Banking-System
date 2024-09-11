package com.rulesservice.service;

import java.util.List;

import com.rulesservice.model.Account;
import com.rulesservice.model.AuthenticationResponse;
import com.rulesservice.model.RulesInput;

public interface RulesService {

	public boolean evaluate(RulesInput account);

	public AuthenticationResponse hasPermission(String token);

	public double serviceCharges(Account account);

	public List<Account> applyServiceCharges(String token);
}
