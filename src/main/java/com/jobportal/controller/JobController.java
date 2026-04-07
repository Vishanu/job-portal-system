package com.jobportal.controller;

import com.jobportal.entity.JobEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.enums.Role;
import com.jobportal.exception.BadRequestException;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        // 1. Validate if recruiter info is provided in the request
        if (job.getRecruiter() == null || job.getRecruiter().getId() == null) {
            throw new BadRequestException("Recruiter ID is required");
        }

        // 2. Fetch the recruiter to verify role
        UserEntity recruiter = userRepository.findById(job.getRecruiter().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found"));

        if (recruiter.getRole() != Role.RECRUITER) { //
            throw new IllegalStateException("Only user with Recruiter role can post jobs");
        }

        // 3. Set verified details
        job.setRecruiter(recruiter); //
        job.setPostedDate(LocalDate.now()); //

        // 4. CALL THE CORRECT METHOD NAME: postJob
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
