package com.icinbank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icinbank.model.Chequebook;

public interface ChequeBookRepository extends JpaRepository<Chequebook, Integer>{

	public List<Chequebook> findByAccount(int account);
}
