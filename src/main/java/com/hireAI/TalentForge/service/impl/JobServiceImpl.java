package com.hireAI.TalentForge.service.impl;

import com.hireAI.TalentForge.entity.Job;
import com.hireAI.TalentForge.repository.JobRepository;
import com.hireAI.TalentForge.service.Interface.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }
    @Override
    public Job create(Job job) {
        Job savedjob=jobRepository.save(job);
        return savedjob;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Job of this particular Id found"));

    }

    @Override
    public Job updateJob(Long Id, Job job) {
        return null;
    }

    @Override
    public void deleteJob(Job job) {
        jobRepository.delete(job);

    }
}
