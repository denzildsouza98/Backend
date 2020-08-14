package com.icinbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.model.User;
import com.icinbank.service.ProfileService;

@RestController
public class ProfileController {

	@Autowired
	private ProfileService service;
	
	@PutMapping("/profile/update")
	public User updateUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		return service.updateUser(user);
	}

	@GetMapping("/profile/{username}")
	public User getUser(@PathVariable("username") String username) {
		// TODO Auto-generated method stub
		return service.getUser(username);
	}
	
}
