package com.icinbank.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icinbank.dao.UserRepository;
import com.icinbank.model.RegisterResponse;
import com.icinbank.model.User;
import com.icinbank.service.AccountService;
import com.icinbank.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	private UserRepository dao;
	
	@Autowired
	private AccountService service;
	
	@Override
	public RegisterResponse createAccount(User user){
		
		RegisterResponse response = new RegisterResponse();
		boolean flag = true;
		String message = "Registration Succesful";
		
		if(usernameAlreadyExists(user.getUsername())) {
			message = "Username already Exists";
			flag = false;
		}
		
		if(flag) {
			String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
			user.setPassword(hashedPassword);
			dao.save(user);
			service.newAccount(user.getUsername());
		}
		response.setRegistrationStatus(flag);
		response.setResponseMessage(message);
		response.setUsername(user.getUsername());
		return response;
		
	}
	
	@Override
	public boolean usernameAlreadyExists(String username) {
		try {
			User u=dao.findByUsername(username);
			System.out.println(u.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}

//	@Override
//	public int generate_primary() {
//		boolean check=false;
//		String acc = "3451783900"+""
//		int no=(int)(Math.random() * (99999999 - 10000000 + 1) + 10000000);
//		List<User> userList=dao.findAll();
//		for(int i=0;i<userList.size();i++) {
//			if(no==userList.get(i).getPri_acc()) {
//				check=true;
//			}
//		}
//		if(check)
//		{
//			return generate_primary();
//		}
//		return no;
//	}
//
//	@Override
//	public int generate_saving() {
//		boolean check=false;
//		int no=(int)(Math.random() * (9999999 - 1000000 + 1) + 1000000);
//		List<User> userList=dao.findAll();
//		for(int i=0;i<userList.size();i++) {
//			if(no==userList.get(i).getSav_acc()) {
//				check=true;
//			}
//		}
//		if(check)
//		{
//			return generate_saving();
//		}
//		return no;
//	}
//
//	@Override
//	public List<User> getAllUsers(){
//		return dao.findAll();	
//	}
	

}