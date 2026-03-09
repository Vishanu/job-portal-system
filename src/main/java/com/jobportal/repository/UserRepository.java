package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
}