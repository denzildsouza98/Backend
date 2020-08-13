package com.icinbank.service;

import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;

public interface AccountService {

	//public Account newAccount(String username);
	public DepositResponse deposit(int acc,int amount);
	public WithdrawResponse withdraw(int acc,int amount);
	public TransferResponse transfer(int saccount,int raccount,int amount);
}
