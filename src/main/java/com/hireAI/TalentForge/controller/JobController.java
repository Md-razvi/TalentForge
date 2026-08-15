package com.hireAI.TalentForge.controller;

import com.hireAI.TalentForge.dto.job.JobCreationResponse;
import com.hireAI.TalentForge.dto.job.JobResponse;
import com.hireAI.TalentForge.entity.Job;
import com.hireAI.TalentForge.service.Interface.JobService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService){
        this.jobService=jobService;
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_RECRUITER')")
    public JobCreationResponse createJob(@RequestBody Job job){
        return jobService.create(job);
    }
    @GetMapping("/all")
    public List<JobResponse> getAllJobs(){
        return jobService.getAllJobs();
    }
    @GetMapping("/{id}")
    public JobResponse getJobById(@PathVariable Long id){
        return  jobService.getJobById(id);
    }


}
