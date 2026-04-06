package com.jobportal.service;

import com.jobportal.entity.ApplicationEntity;

import java.util.List;

public interface ApplicationService {
    ApplicationEntity applyJob(ApplicationEntity application);

    List<ApplicationEntity> getAllApplications();
}
