package com.icinbank.service;

import com.icinbank.model.LoginDetails;
import com.icinbank.model.LoginResponse;

public interface LoginService {

	public LoginResponse customerLogin(LoginDetails details);
}
