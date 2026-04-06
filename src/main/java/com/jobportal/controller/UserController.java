package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobportal.service.UserService;
import com.jobportal.entity.UserEntity;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public UserEntity registerUser(@RequestBody UserEntity user) {
        return userService.registerUser(user);
	}
	
	@GetMapping
	public List<UserEntity> getAllUsers(){
		return userService.getAllUsers();
	}
}
