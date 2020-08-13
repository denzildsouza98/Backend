package com.icinbank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.model.Transfer;

@Repository
public interface TransferHistoryRepository extends JpaRepository<Transfer, Integer>{

	public List<Transfer> findBySaccount(int saccount);
	public List<Transfer> findByRaccount(int raccount);
	
}
