package com.dk.jobby.jobpost.controller;

import com.dk.jobby.jobpost.service.JobService; // ✅ Ensure this import is correct
import com.dk.jobby.jobpost.domain.JobListing;
import com.dk.jobby.jobpost.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/job-postings")
public class JobController {
    @Autowired
    private JobService jobService; // ✅ Ensure JobService is properly injected

    @GetMapping("/post")
    public String showPostJobPage(HttpSession session, Model model) {
        User employer = (User) session.getAttribute("user");
        if (employer == null) {
            return "redirect:/?error=Please login first";
        }
        return "post_job";
    }

    @PostMapping("/post")
    public String postJob(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String salary,
                          @RequestParam String requirements,
                          @RequestParam String location,
                          @RequestParam String jobType,
                          HttpSession session) {
        User employer = (User) session.getAttribute("user");
        if (employer == null) {
            return "redirect:/?error=Please login first";
        }

        JobListing job = new JobListing(title, description, salary, requirements, location, jobType, employer);
        jobService.createJob(job);
        return "redirect:/job-postings/list?success=Job posted successfully!";
    }

    @GetMapping("/list")
    public String listJobs(HttpSession session, Model model) {
        User employer = (User) session.getAttribute("user");
        if (employer == null) {
            return "redirect:/?error=Please login first";
        }

        List<JobListing> jobs = jobService.getJobsByEmployer(employer);
        model.addAttribute("jobs", jobs);
        return "job_list";
    }
}
