package com.dk.jobby.jobpost.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_listings")
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String salary;
    private String requirements;
    private String location;
    private String jobType;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    public JobListing() {}

    public JobListing(String title, String description, String salary, String requirements, String location, String jobType, User employer) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.requirements = requirements;
        this.location = location;
        this.jobType = jobType;
        this.employer = employer;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }
    public User getEmployer() { return employer; }
    public void setEmployer(User employer) { this.employer = employer; }
}
