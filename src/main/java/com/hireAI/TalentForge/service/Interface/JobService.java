package com.hireAI.TalentForge.service.Interface;

import com.hireAI.TalentForge.dto.job.JobCreationResponse;
import com.hireAI.TalentForge.dto.job.JobResponse;
import com.hireAI.TalentForge.entity.Job;

import java.util.List;

public interface JobService {
    JobCreationResponse create (Job job);
    List <JobResponse> getAllJobs();
    JobResponse getJobById(Long id);
    Job updateJob(Long Id,Job job);
    void deleteJob(Job job);
}
