package com.repository;

import com.core.repository.BaseRepository;
import com.model.User;

public interface UserRepository extends BaseRepository<User, Integer> {

	User findByUserName(String userName);
	User findByEmail(String email);
	User findByCertificate(String certificate);
	User findByPhone(String phone);
}
