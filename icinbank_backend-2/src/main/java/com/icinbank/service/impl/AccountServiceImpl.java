package com.icinbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.AccountRepository;
import com.icinbank.model.Account;
import com.icinbank.service.AccountService;
import com.icinbank.service.TransferHistoryService;
import com.icinbank.service.UserHistoryService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository dao;
	
	@Autowired
	private UserHistoryService service;
	
	@Autowired
	private TransferHistoryService tservice;

	@Override
	public Account deposit(int acc, int amount) {
		Account account=dao.findByAccno(acc);
		account.setBalance(account.getBalance()+amount);
		service.addAction(acc, amount, account.getBalance(), "deposit");
		return dao.save(account);
	}

	@Override
	public Account withdraw(int acc, int amount) {
		Account account=dao.findByAccno(acc);
		account.setBalance(account.getBalance()-amount);
		service.addAction(acc, amount, account.getBalance(), "withdraw");
		return dao.save(account);
	}

	@Override
	public void transfer(int saccount, int raccount, int amount) {
		
		Account senderAccount=dao.findByAccno(saccount);
		Account receiverAccount=dao.findByAccno(raccount);
		
		senderAccount.setBalance(senderAccount.getBalance()-amount);
		receiverAccount.setBalance(receiverAccount.getBalance()+amount);
		tservice.addAction(saccount, raccount, amount);
		dao.save(senderAccount);
		dao.save(receiverAccount);
	}
	
	
//	@Override
//	public Account newAccount(String username) {
//		Account account=new Account();
//		account.setUsername(username);
//		account.setAccno(generate_saving());
//		return dao.save(account);
//	}
//
//	public int generate_saving() {
//		boolean check=false;
//		int no=(int)(Math.random() * (9999999 - 1000000 + 1) + 1000000);
//		List<Account> accountList=(List<Account>) dao.findAll();
//		for (int i = 0; i < accountList.size(); i++) {
//			if(accountList.get(i).getAccno()==no) {
//				check=true;
//			}
//		}
//		if(check)
//		{
//			return generate_saving();
//		}
//		return no;
//	}

}
