package com.jobportal.serviceImp;

import java.time.LocalDate;
import java.util.List;

import com.jobportal.entity.JobEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.enums.ApplicationStatus;
import com.jobportal.exception.BadRequestException;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.ApplicationEntity;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.service.ApplicationService;

@Service
public class ApplicationServiceImp implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ApplicationEntity applyJob(ApplicationEntity application) {
        Integer jobId = application.getJob().getId();
        Integer userId = application.getUser().getId();

        // 1. Check for duplicates
        if(applicationRepository.existsByJobIdAndUserId(jobId, userId)){
            throw new BadRequestException("User already applied for this job");
        }

        // 2. Fetch managed entities
        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 3. Set data and save
        application.setJob(job);
        application.setUser(user);
        application.setAppliedDate(LocalDate.now()); // Ensure date is set
        application.setStatus(ApplicationStatus.APPLIED); // Default status

        return applicationRepository.save(application);
    }

    @Override
    public List<ApplicationEntity> getAllApplications() {
        return applicationRepository.findAll();
    }
}