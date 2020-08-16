package com.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admin.model.User;
import com.admin.model.UserDisplay;

public interface UserDisplayRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT new com.admin.model.UserDisplay(u.fname,u.lname,u.phone,u.username,u.status,a.accno,a.balance,s.accno,s.balance)" + "FROM User u ,Account a,Saccount s WHERE u.username=a.username and u.username=s.username")
	public List<UserDisplay> getAllUsers();

}
