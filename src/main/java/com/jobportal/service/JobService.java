package com.jobportal.service;

import com.jobportal.entity.JobEntity;

import java.util.List;

public interface JobService {
    List<JobEntity> findByLocation(String location);

    JobEntity postJob(JobEntity job);

    List<JobEntity> getAllJobs();

    JobEntity fetchById(Integer id);

    JobEntity updateJob(Integer id, JobEntity job);

    void deleteJob(Integer id);
}
