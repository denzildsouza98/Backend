package com.icinbank.service;
import com.icinbank.model.User;
import com.icinbank.response.RegisterResponse;

public interface RegistrationService {

	public RegisterResponse createAccount(User details); 
	public boolean usernameAlreadyExists(String username);
}
