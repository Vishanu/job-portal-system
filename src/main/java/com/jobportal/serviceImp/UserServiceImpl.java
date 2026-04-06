package com.jobportal.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.UserEntity;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserEntity registerUser(UserEntity user) {
		return userRepository.save(user);
	}
	
	@Override
	public List<UserEntity> getAllUsers(){
		return userRepository.findAll();
	}
}
