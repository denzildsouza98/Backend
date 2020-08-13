package com.icinbank.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.icinbank.model.TransactionDetails;
import com.icinbank.model.Transfer;
import com.icinbank.model.TransferDetails;
import com.icinbank.model.UserHistory;
import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;
import com.icinbank.service.AccountService;
import com.icinbank.service.TransferHistoryService;
import com.icinbank.service.UserHistoryService;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired
	private UserHistoryService hservice;
	
	@Autowired
	private TransferHistoryService tservice;
	
	@PostMapping("/deposit")
	public DepositResponse deposit(@RequestBody TransactionDetails details) {
		return service.deposit(details.getAccount(), details.getAmount());
	}

	@PostMapping("/withdraw")
	public WithdrawResponse withdraw(@RequestBody TransactionDetails details) {
		return service.withdraw(details.getAccount(), details.getAmount());
	}

	@PostMapping("/transfer")
	public TransferResponse transfer(@RequestBody TransferDetails details) {
		return service.transfer(details.getSaccount(), details.getRaccount(), details.getAmount());
	}
	
	@GetMapping("/getHistory/{account}")
	public List<UserHistory> getHistory(@PathVariable("account") int account )
	{
		List<UserHistory> history=hservice.getHistory(account);
		Collections.reverse(history);
		return history;
	}
	
	@GetMapping("/getTransfers/{account}")
	public List<Transfer> getTransfers(@PathVariable("account") int account )
	{
		return tservice.getTransfers(account);
	}
}
