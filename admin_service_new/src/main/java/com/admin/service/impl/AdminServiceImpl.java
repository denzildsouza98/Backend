package com.admin.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.ChequeBookRequestsRepository;
import com.admin.dao.TransferRepository;
import com.admin.dao.UserDisplayRepository;
import com.admin.dao.UserRepository;
import com.admin.model.ChequebookRequest;
import com.admin.model.Transfer;
import com.admin.model.User;
import com.admin.model.UserDisplay;
import com.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private TransferRepository transDAO;
	
	@Autowired
	private ChequeBookRequestsRepository chequeBookDAO;
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private UserDisplayRepository displayDao;
	
	@Override
	public void authorizeUser(String username) {
		userDao.authorizeUse(username);
	}
	
	@Override
	public void cancelAuthorization(String username) {
		userDao.cancelAuthorization(username);
	}

	@Override
	public void acceptChequebookRequest(int accNo) {
		chequeBookDAO.setChequebookInfoByAccount(accNo);
		
	}

	@Override
	public void enableUser(String username) {
		userDao.enableUser(username);
		
	}

	@Override
	public void disableUser(String username) {
		userDao.disableUser(username);
		
	}

	@Override
	public List<UserDisplay> getAllUsers() {
		return displayDao.getAllUsers();
	}

	@Override
	public List<Transfer> getAllTransactions(long accountNo) {
		List<Transfer> sender=transDAO.findBySaccount(accountNo);
		List<Transfer> receiver=transDAO.findByRaccount(accountNo);
		List<Transfer> merged=new ArrayList<>();
		merged.addAll(sender);
		merged.addAll(receiver);
		Collections.sort(merged);
		return merged;
	}

	@Override
	public List<ChequebookRequest> getAllChequebookRequests() {
		return chequeBookDAO.findAllChequebookRequests();
	}

	@Override
	public List<User> getAllUnauthorizedUsers() {
		return userDao.findAllUnauthorizedAccounts();
	}

	@Override
	public void setUserFeatures(String username, int featureId) {
		userDao.setUserFeatureStatus(username,featureId);
		
	}

}
