package com.icinbank.service;

import java.util.List;

import com.icinbank.model.UserHistory;

public interface UserHistoryService {

	public UserHistory addAction(int account,int amount,int balance,String action);
	public List<UserHistory> getHistory(int account);
}
