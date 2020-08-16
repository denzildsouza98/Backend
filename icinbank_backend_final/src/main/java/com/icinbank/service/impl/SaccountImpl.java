package com.icinbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.AccountRepository;
import com.icinbank.dao.SaccountRepository;
import com.icinbank.dao.UserRepository;
import com.icinbank.model.Account;
import com.icinbank.model.Saccount;
import com.icinbank.model.User;
import com.icinbank.response.DepositResponse;
import com.icinbank.response.TransferResponse;
import com.icinbank.response.WithdrawResponse;
import com.icinbank.service.SaccountService;
import com.icinbank.service.TransferHistoryService;
import com.icinbank.service.UserHistoryService;

@Service
public class SaccountImpl implements SaccountService{

	@Autowired
	private SaccountRepository dao;
	
	@Autowired
	private UserHistoryService service;
	
	@Autowired
	private TransferHistoryService tservice;
	
	@Autowired
	private AccountRepository adao;
	
	@Autowired
	private UserRepository udao;
	
	@Override
	public Saccount newAccount(String username) {
		Saccount account =new Saccount();
		account.setUsername(username);
		account.setAccno(generate_saving());
		User user=udao.findByUsername(username);
		account.setUser(user);	
		return dao.save(account);
	}

	public int generate_saving() {
		boolean check=false;
		int no=(int)(Math.random() * (99999999 - 10000000 + 1) + 10000000);
		List<Saccount> accountList=(List<Saccount>) dao.findAll();
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

	@Override
	public DepositResponse deposit(int acc, int amount) {
		DepositResponse response=new DepositResponse();
		boolean flag=true;
		try {
			Saccount account=dao.findByAccno(acc);
			account.setBalance(account.getBalance()+amount);
			service.addAction(acc, amount, account.getBalance(), "deposit");
			dao.save(account);
			response.setResponseMessage("Rs."+amount+" successfully deposited into your account balance is now Rs."+account.getBalance());
			response.setDepositStatus(flag);
		} catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setDepositStatus(flag);
		}
		response.setAccount(acc);
		return response; 
	}

	@Override
	public WithdrawResponse withdraw(int acc, int amount) {
		WithdrawResponse response=new WithdrawResponse();
		boolean flag=true;
		
		try {
			Saccount account=dao.findByAccno(acc);
			User user=udao.findByUsername(account.getUsername());
			if(user.getFeatureStatus()==2 || user.getFeatureStatus()==3)
			{
			
			if(account.getBalance()>=amount) 
				{
				account.setBalance(account.getBalance()-amount);
				service.addAction(acc, amount, account.getBalance(), "withdraw");
				dao.save(account);
				response.setResponseMessage("Rs."+amount+" successfully withdrawn your account balance is now Rs."+account.getBalance());
				response.setWithdrawStatus(flag);
				}
			else 
				{
				flag=false;
				response.setResponseMessage("Insufficient funds to complete the transaction");
				response.setWithdrawStatus(flag);
				}
			}
			else {
				flag=false;
				response.setResponseMessage("This function is not available for your account");
				response.setWithdrawStatus(flag);
			}
		} catch (Exception e) {
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setWithdrawStatus(flag);
		}
		response.setAccount(acc);
		return response;
	}

	@Override
	public TransferResponse transfer(int saccount, int raccount, int amount) {
		TransferResponse response=new TransferResponse();
		boolean flag=true;
		try {
			Saccount senderAccount=dao.findByAccno(saccount);
			String len = Integer.toString(raccount);
			if(len.length()==7) 
			{
				Account receiverAccount=adao.findByAccno(raccount);
				if(senderAccount.getAccno()!=receiverAccount.getAccno()) 
				{
				if(senderAccount.getBalance()>=amount) {
					User user=udao.findByUsername(senderAccount.getUsername());
					
					if(user.getFeatureStatus()==3) 
					{
						senderAccount.setBalance(senderAccount.getBalance()-amount);
						receiverAccount.setBalance(receiverAccount.getBalance()+amount);
						tservice.addAction(saccount, raccount, amount);
						dao.save(senderAccount);
						adao.save(receiverAccount);
						response.setResponseMessage("Rs."+amount+" successfully transferred to account "+receiverAccount.getAccno());
						response.setTransferStatus(flag);
					}
					else {
						flag=false;
						response.setResponseMessage("This feature is not available for your account");
						response.setTransferStatus(flag);
					}
				}
				else 
					{
					flag=false;
					response.setResponseMessage("Insufficient funds to complete the transfer");
					response.setTransferStatus(flag);
					}
			}
			else {
				flag=false;
				response.setResponseMessage("sender and recieiver accounts are same");
				response.setTransferStatus(flag);
			}
			}
			else
			{
				Saccount receiverAccount=dao.findByAccno(raccount);
				if(senderAccount.getAccno()!=receiverAccount.getAccno()) 
				{
					
					if(senderAccount.getBalance()>amount) {
						User user=udao.findByUsername(senderAccount.getUsername());
						
						if(user.getFeatureStatus()==3) 
							{
								senderAccount.setBalance(senderAccount.getBalance()-amount);
								receiverAccount.setBalance(receiverAccount.getBalance()+amount);
								tservice.addAction(saccount, raccount, amount);
								dao.save(senderAccount);
								dao.save(receiverAccount);
								response.setResponseMessage("Rs."+amount+" successfully transferred to account "+receiverAccount.getAccno());
								response.setTransferStatus(flag);
							}
						else {
								flag=false;
								response.setResponseMessage("This function isnt available for the account");
								response.setTransferStatus(flag);
							}
						}
					
					else {
							flag=false;
							response.setResponseMessage("Insufficient funds to complete the transfer");
							response.setTransferStatus(flag);
						}
				}
				
				else {
						flag=false;
						response.setResponseMessage("sender and recieiver accounts are same");
						response.setTransferStatus(flag);
				}
			}
		} 
		catch (Exception e) 
		{
			flag=false;
			response.setResponseMessage("Account number is incorrect");
			response.setTransferStatus(flag);
		}
		
		response.setSaccount(saccount);
	return response;
	}

	@Override
	public Saccount getAccount(String username) {
		try {
			return dao.findByUsername(username);
		} catch (Exception e) {
			return null;
		}
		
	}

	
}
