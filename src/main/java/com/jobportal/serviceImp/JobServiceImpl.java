package com.jobportal.serviceImp;

import java.util.List;

import com.jobportal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.JobEntity;
import com.jobportal.repository.JobRepository;
import com.jobportal.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;

    @Override
    public List<JobEntity> findByLocation(String location) {
        return jobRepository.findByLocationIgnoreCase(location);
    }

    public JobEntity postJob(JobEntity job) {
		return jobRepository.save(job);
	}
	
	public List<JobEntity> getAllJobs(){
		return jobRepository.findAll();
	}

    @Override
    public JobEntity fetchById(Integer id) {
        return jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job not found with id: " + id));
    }

    @Override
    public JobEntity updateJob(Integer id, JobEntity job) {
        JobEntity existingJob = jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job not found with id: "+ id));
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setSalary(job.getSalary());
        existingJob.setLocation(job.getLocation());
        existingJob.setCompanyName(job.getCompanyName());
        existingJob.setPostedDate(job.getPostedDate());

        return jobRepository.save(existingJob);
    }

    @Override
    public void deleteJob(Integer id) {
        JobEntity job = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Job not found with id: "+ id));
        jobRepository.delete(job);
    }
}
