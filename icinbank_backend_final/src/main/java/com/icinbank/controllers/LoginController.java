package com.icinbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.details.LoginDetails;
import com.icinbank.response.LoginResponse;
import com.icinbank.service.impl.LoginServiceImpl;

@RestController
public class LoginController {
	
	@Autowired
	LoginServiceImpl service;
	
	@PostMapping("/login")
	public LoginResponse userLogin(@RequestBody LoginDetails details) {
		
		return service.customerLogin(details);
		
	}

}