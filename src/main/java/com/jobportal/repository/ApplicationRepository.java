package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.entity.ApplicationEntity;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer>{
	
}