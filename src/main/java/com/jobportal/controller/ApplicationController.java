package com.jobportal.controller;

import com.jobportal.entity.ApplicationEntity;
import com.jobportal.entity.JobEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.exception.BadRequestException;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ApplicationEntity applyJob(@RequestBody ApplicationEntity application){
        Integer jobId = application.getJob().getId();
        Integer userId = application.getUser().getId();

        if(applicationRepository.existsByJobIdAndUserId(jobId, userId)){
            throw new BadRequestException("User already applied for this job");
        }

        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        application.setJob(job);
        application.setUser(user);

        return applicationService.applyJob(application);
    }

    @GetMapping
    public List<ApplicationEntity> getAllApplications(){
        return applicationService.getAllApplications();
    }
}
