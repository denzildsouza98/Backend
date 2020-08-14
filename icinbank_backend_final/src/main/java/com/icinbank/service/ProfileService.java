package com.icinbank.service;

import com.icinbank.model.User;

public interface ProfileService {
	public User updateUser(User user);
	public User getUser(String username);
}
