package com.hireAI.TalentForge.service.impl;

import com.hireAI.TalentForge.dto.job.JobCreationResponse;
import com.hireAI.TalentForge.dto.job.JobResponse;
import com.hireAI.TalentForge.entity.Job;
import com.hireAI.TalentForge.entity.User;
import com.hireAI.TalentForge.repository.JobRepository;
import com.hireAI.TalentForge.security.CustomUserDetails;
import com.hireAI.TalentForge.service.Interface.JobService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }
    @Override
    public JobCreationResponse create(Job job) {
        CustomUserDetails userDetails=(CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User recruiter=userDetails.getUser();
        job.setRecruiter(recruiter);
        Job savedJob=jobRepository.save(job);
        return new JobCreationResponse(true,
                "Job has been successfully created",
                String.valueOf(savedJob.getId()));

    }

    @Override
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream().map(this::toJobResponse).toList();
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job= jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Job of this particular Id found"));
        return toJobResponse(job);
    }

    @Override
    public Job updateJob(Long Id, Job job) {
        return null;
    }

    @Override
    public void deleteJob(Job job) {
        jobRepository.delete(job);

    }
    private JobResponse toJobResponse(Job job){
            JobResponse jobResponse=new JobResponse();
            jobResponse.setId(job.getId());
            jobResponse.setDescription(job.getDescription());
            jobResponse.setSalary(job.getSalary());
            jobResponse.setLocation(job.getLocation());
            jobResponse.setTitle(job.getTitle());
            String firstName=job.getRecruiter().getFirstName();
            String middleName=job.getRecruiter().getMiddleName();
            String lastName=job.getRecruiter().getLastName();
            String fullName=firstName+" "+(middleName!=null?middleName+" ":" ")+lastName;
            jobResponse.setRecruiterName(fullName);
            return jobResponse;
    }
}
