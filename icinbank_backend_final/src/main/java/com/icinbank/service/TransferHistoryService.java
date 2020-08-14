package com.icinbank.service;

import java.util.List;

import com.icinbank.model.Transfer;

public interface TransferHistoryService {

	public Transfer addAction(int saccount, int raccount, int amount);
	public List<Transfer> getTransfers(int account);
}
