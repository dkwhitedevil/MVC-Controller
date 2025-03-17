package com.dk.jobby.jobpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dk.jobby.jobpost.domain.JobListing;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobListing, Long> {
    List<JobListing> findByEmployerId(Long employerId);
}
