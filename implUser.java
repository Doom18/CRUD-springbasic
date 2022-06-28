package com.example.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repo.UserRepository;

@Service
public class implUser implements UserService{
	@Autowired
	UserRepository ur;

	@Override
	public List<User> getAUser() {
		return ur.findAll();
	}
	@Override
	public void saveUser(User user) {
		this.ur.save(user);
	}

	@Override
	public User getUserById(long id) {
		Optional<User> optional=ur.findById(id);
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();
		}
		else 
		throw new RuntimeException("User with this ID not found :: " + id);
		return user;
	}
	@Override
	public void delUser(long id) {
		this.ur.deleteById(id);;
	} 
}
