package com.icinbank.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.AccountRepository;
import com.icinbank.dao.ChequeBookRepository;
import com.icinbank.dao.SaccountRepository;
import com.icinbank.model.Account;
import com.icinbank.model.Chequebook;
import com.icinbank.model.Saccount;
import com.icinbank.response.ChequeResponse;
import com.icinbank.service.ChequebookService;

@Service
public class ChequebookServiceimpl implements ChequebookService{
	
	@Autowired
	private ChequeBookRepository dao;
	
	@Autowired 
	private AccountRepository adao;
	
	@Autowired
	private SaccountRepository sdao;

	@Override
	public ChequeResponse createrequest(Chequebook chequebook) {
		ChequeResponse response=new ChequeResponse();
		int account = chequebook.getAccount();
		LocalDate today = LocalDate.now();
		String len=Integer.toString(account);
		if(len.length()==7) 
		{
			try {
			Account account1 =adao.findByAccno(account);
			response.setAccount(account1.getAccno());
			response.setStatus(true);
			response.setResponseMessage("Request submitted successfully");
			chequebook.setAccType("Primary");
			chequebook.setDate(today);
			chequebook.setRequestStatus(false);
			dao.save(chequebook);
			
			}
			catch(Exception e) {
				response.setAccount(account);
				response.setStatus(false);
				response.setResponseMessage("account number is incorrect");
			}
		}
		else 
		{
			if(len.length()==8) {
				try {
					Saccount saccount=sdao.findByAccno(account);
					response.setAccount(saccount.getAccno());
					response.setStatus(true);
					response.setResponseMessage("Request submitted successfully");
					chequebook.setRequestStatus(false);
					chequebook.setAccType("Secondary");
					chequebook.setDate(today);
					dao.save(chequebook);
					} 
				catch (Exception e) {
					response.setAccount(account);
					response.setStatus(false);
					response.setResponseMessage("account number is incorrect");
					}
		}
		else
		{
			response.setAccount(account);
			response.setStatus(false);
			response.setResponseMessage("account number is incorrect");
		}
		
	}
		return response;
	}

	@Override
	public List<Chequebook> getRequests(int account) {
		// TODO Auto-generated method stub
		return dao.findByAccount(account);
	}

	

}
