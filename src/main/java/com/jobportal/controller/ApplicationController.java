package com.jobportal.controller;

import com.jobportal.entity.ApplicationEntity;
import com.jobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ApplicationEntity applyJob(@RequestBody ApplicationEntity application){
        // Move all validation and fetching logic to the Service Layer
        return applicationService.applyJob(application);
    }

    @GetMapping
    public List<ApplicationEntity> getAllApplications(){
        return applicationService.getAllApplications();
    }
}
