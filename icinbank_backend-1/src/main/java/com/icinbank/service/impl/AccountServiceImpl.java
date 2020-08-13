package com.icinbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.AccountRepository;
import com.icinbank.model.Account;
import com.icinbank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository dao;
	
	@Override
	public Account newAccount(String username) {
		Account account=new Account();
		account.setUsername(username);
		account.setAccno(generate_saving());
		return dao.save(account);
	}

	public int generate_saving() {
		boolean check=false;
		int no=(int)(Math.random() * (9999999 - 1000000 + 1) + 1000000);
		List<Account> accountList=(List<Account>) dao.findAll();
		for (int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i).getAccno()==no) {
				check=true;
			}
		}
		if(check)
		{
			return generate_saving();
		}
		return no;
	}

	


}
