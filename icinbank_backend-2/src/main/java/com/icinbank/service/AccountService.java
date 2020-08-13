package com.icinbank.service;

import com.icinbank.model.Account;

public interface AccountService {

	//public Account newAccount(String username);
	public Account deposit(int acc,int amount);
	public Account withdraw(int acc,int amount);
	public void transfer(int saccount,int raccount,int amount);
}
