package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.entity.JobEntity;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Integer>{

    List<JobEntity> findByLocationIgnoreCase(String location);

}