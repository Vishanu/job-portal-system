package com.jobportal.service;

import java.util.List;

import com.jobportal.entity.UserEntity;

public interface UserService {
	
	UserEntity registerUser(UserEntity user);
	
	List<UserEntity> getAllUsers();
}
