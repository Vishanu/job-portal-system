package com.jobportal.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.ApplicationEntity;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.service.ApplicationService;

@Service
public class ApplicationServiceImp implements ApplicationService{
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public ApplicationEntity applyJob(ApplicationEntity application) {
		return applicationRepository.save(application);
	}
	
	public List<ApplicationEntity> getAllApplications(){
		return applicationRepository.findAll();
	}
}
