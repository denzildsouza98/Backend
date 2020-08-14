package com.icinbank.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.icinbank.model.User;
import com.icinbank.response.RegisterResponse;
import com.icinbank.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;

	@PostMapping("/register")
	public RegisterResponse createUser(@RequestBody User user) {

		return service.createAccount(user);
	}

}