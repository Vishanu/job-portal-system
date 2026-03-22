package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.entity.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, Integer>{
	
}