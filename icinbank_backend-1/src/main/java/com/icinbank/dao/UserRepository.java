package com.icinbank.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icinbank.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsername(String username);
}
