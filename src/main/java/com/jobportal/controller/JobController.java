package com.jobportal.controller;

import com.jobportal.entity.JobEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.exception.BadRequestException;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public JobEntity postJob(@RequestBody JobEntity job){
        Integer recruiterId = job.getRecruiter().getId();

        UserEntity recruiter = userRepository.findById(recruiterId).orElseThrow(()-> new ResourceNotFoundException("Recruiter not found"));
        if(!recruiter.getRole().equals("RECRUITER")){
            throw new BadRequestException("Only recruiter can post jobs");
        }
        job.setRecruiter(recruiter);
        return jobService.postJob(job);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobEntity> getJobById(@PathVariable Integer id){
        return ResponseEntity.ok(jobService.fetchById(id));
    }

    @GetMapping
    public List<JobEntity> getAllJobs(){
        return jobService.getAllJobs();
    }

    @PutMapping("/{id}")
    public JobEntity updateJob(@PathVariable Integer id, @RequestBody JobEntity job){
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Integer id){
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }

    @GetMapping("/search")
    public List<JobEntity> searchByLocation(@RequestParam String location){
        return jobService.findByLocation(location);
    }


}
