package com.icinbank.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.dao.UserRepository;
import com.icinbank.model.LoginDetails;
import com.icinbank.model.LoginResponse;
import com.icinbank.model.User;
import com.icinbank.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserRepository dao;
	
	@Override
	public LoginResponse customerLogin(LoginDetails login){
		LoginResponse response = new LoginResponse();
		boolean flag = true;
		String message = "Login succesfull";
		User user = null;
		String hashedPassword = DigestUtils.sha256Hex(login.getPassword());
		try {
			user=dao.findByUsername(login.getUsername());
			if(user.isStatus()) {
				flag = false;
				message = "Dear Mr/Ms."+user.getFname()+" your account has been blocked for security reasons.";
			}
			if(!hashedPassword.equals(user.getPassword())) {
				flag = false;
				message = "Username or password is incorrect";
			}
		} 
		catch (Exception e) {
			flag = false;
			message = "Username or password is incorrect";
		}
		
		response.setLoginStatus(flag);
		response.setResponseMessage(message);
		response.setUsername(user.getUsername());
		return response;
	}

}