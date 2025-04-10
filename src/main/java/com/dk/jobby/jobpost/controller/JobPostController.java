package com.dk.jobby.jobpost.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dk.jobby.jobpost.domain.JobPost;
import com.dk.jobby.jobpost.domain.User;
import com.dk.jobby.jobpost.service.JobPostService;
import com.dk.jobby.jobpost.service.UserService;

@Controller
@RequestMapping("/job-postings")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewJobPostings(Model model) {
        List<JobPost> jobPosts = jobPostService.getAllJobPosts();
        model.addAttribute("jobPosts", jobPosts);
        return "job-listings";
    }

    @GetMapping("/create")
    public String createJobPostForm(Model model) {
        model.addAttribute("jobPost", new JobPost());
        return "create-job";
    }

    @PostMapping
    public String saveJobPost(@ModelAttribute JobPost jobPost) {
        jobPostService.saveJobPost(jobPost);
        return "redirect:/job-postings";
    }

    @GetMapping("/job-post")
    public String viewJobPost(Model model) {
        List<JobPost> jobPosts = jobPostService.getAllJobPosts();
        model.addAttribute("jobPosts", jobPosts);
        return "job-listings";
    }

    @GetMapping("/job-post/create")
    public String redirectToCreateJobPage(Model model) {
        model.addAttribute("jobPost", new JobPost());
        return "create-job";
    }

    @GetMapping("/home")
    public String viewHomePage(Model model) {
        int jobCount = jobPostService.getAllJobPosts().size();
        model.addAttribute("jobCount", jobCount);

        // Fetch logged-in user details
        User loggedInUser = userService.getLoggedInUser();
        model.addAttribute("userName", loggedInUser.getName());
        model.addAttribute("userEmail", loggedInUser.getEmail());

        return "home";
    }

    @GetMapping("/profile")
    public String viewProfilePage(Model model) {
        // Fetch logged-in user details
        User loggedInUser = userService.getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user) {
        // Update user details
        userService.updateUser(user);
        return "redirect:/job-postings/home";
    }
}