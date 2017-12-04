package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.repository.BaseRepository;
import com.core.service.AbstractService;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@Service
public class UserService extends AbstractService<User,Integer>{

	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User findByUserName(String userName) {
		
		return repository.findByUserName(userName);
	}
	
	@Override
	public void create(User user) throws Exception {

		repository.save(user);
	}
	
	
	@Override
	public UserRepository getRepository() {
		
		return repository;
	}
	
	
}
