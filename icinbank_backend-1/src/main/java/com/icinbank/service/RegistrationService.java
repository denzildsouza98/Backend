package com.icinbank.service;

import com.icinbank.model.RegisterResponse;
import com.icinbank.model.User;

public interface RegistrationService {

	public RegisterResponse createAccount(User details); 
	public boolean usernameAlreadyExists(String username);
}
