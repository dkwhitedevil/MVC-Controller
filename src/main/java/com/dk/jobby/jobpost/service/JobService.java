package com.dk.jobby.jobpost.service;

import com.dk.jobby.jobpost.domain.JobListing;
import com.dk.jobby.jobpost.domain.User;
import com.dk.jobby.jobpost.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public JobListing createJob(JobListing job) {
        return jobRepository.save(job);
    }

    public List<JobListing> getJobsByEmployer(User employer) {
        return jobRepository.findByEmployerId(employer.getId());
    }

    public JobListing getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public JobListing updateJob(JobListing job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
