package com.repository;

import com.core.repository.BaseRepository;
import com.model.User;

public interface UserRepository extends BaseRepository<User, Integer> {

	User findByUserName(String userName);
	
}
