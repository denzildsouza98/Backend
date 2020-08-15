package com.icinbank.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.details.TransactionDetails;
import com.icinbank.details.TransferDetails;
import com.icinbank.model.Account;
import com.icinbank.model.Saccount;
import com.icinbank.model.Transfer;
import com.icinbank.model.UserHistory;
import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;
import com.icinbank.service.AccountService;
import com.icinbank.service.SaccountService;
import com.icinbank.service.TransferHistoryService;
import com.icinbank.service.UserHistoryService;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired 
	private SaccountService sservice;
	
	@Autowired
	private UserHistoryService hservice;
	
	@Autowired
	private TransferHistoryService tservice;
	
	@PutMapping("/account/profile")
	public Account updateProfile(@RequestBody Account account) {
		return service.updateAccount(account);
	}
	
	@GetMapping("/account/getprimary/{username}")
	public Account getPrimarydetails(@PathVariable("username") String username) {
		return service.getAccount(username);
	}
	
	@GetMapping("/account/getsaving/{username}")
	public Saccount getSavingdetails(@PathVariable("username") String username) {
		return sservice.getAccount(username);
	}
	
	@GetMapping("/account/details/{account}")
	public Account getAccountDetails(@PathVariable("account") int account ) {
		
		return service.getAccountDetails(account);
		
	}
	
	@PostMapping("/account/deposit")
	public DepositResponse deposit(@RequestBody TransactionDetails details) {
		String len = Integer.toString(details.getAccount());
		if(len.length()==7) {
			return service.deposit(details.getAccount(), details.getAmount());
		}
		else {
			return sservice.deposit(details.getAccount(), details.getAmount());
		}
	}

	@PostMapping("/account/withdraw")
	public WithdrawResponse withdraw(@RequestBody TransactionDetails details) {
		String len = Integer.toString(details.getAccount());
		if(len.length()==7) {
		return service.withdraw(details.getAccount(), details.getAmount());
		}
		else {
			return sservice.withdraw(details.getAccount(), details.getAmount());
		}
	}

	@PostMapping("/account/transfer")
	public TransferResponse transfer(@RequestBody TransferDetails details) {
		String len = Integer.toString(details.getSaccount());
		if(len.length()==7) {
		return service.transfer(details.getSaccount(), details.getRaccount(), details.getAmount());
		}
		else
		{
			return sservice.transfer(details.getSaccount(), details.getRaccount(), details.getAmount());
		}
	}
	
	@GetMapping("/account/getHistory/{account}")
	public List<UserHistory> getHistory(@PathVariable("account") int account )
	{
		List<UserHistory> history=hservice.getHistory(account);
		Collections.reverse(history);
		return history;
	}
	
	@GetMapping("/account/getTransfers/{account}")
	public List<Transfer> getTransfers(@PathVariable("account") int account )
	{
		return tservice.getTransfers(account);
	}
}
