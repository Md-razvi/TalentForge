package com.hireAI.TalentForge.service.Interface;

import com.hireAI.TalentForge.entity.Job;

import java.util.List;

public interface JobService {
    Job create (Job job);
    List <Job> getAllJobs();
    Job getJobById(Long id);
    Job updateJob(Long Id,Job job);
    void deleteJob(Job job);
}
