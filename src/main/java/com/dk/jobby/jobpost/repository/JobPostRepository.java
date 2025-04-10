package com.dk.jobby.jobpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dk.jobby.jobpost.domain.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}