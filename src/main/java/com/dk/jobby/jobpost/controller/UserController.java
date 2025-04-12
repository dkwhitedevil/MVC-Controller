package com.dk.jobby.jobpost.controller;

import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dk.jobby.jobpost.domain.User;
import com.dk.jobby.jobpost.service.UserService;

@Controller
public class UserController 
{
    @Autowired
    private UserService userService;
    
    @GetMapping("/profile")
    public String showProfile(Model model) 
    {
        User loggedInUser = userService.getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("errors", new HashMap<String, String>());
        model.addAttribute("success", "");
        model.addAttribute("error", "");
        return "profile";
    }
    
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user, BindingResult result, Model model) {
        // Validate required fields
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            result.addError(new FieldError("user", "username", "Username is required."));
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            result.addError(new FieldError("user", "email", "Email is required."));
        }
        if (user.getCompanyName() == null || user.getCompanyName().isEmpty()) {
            result.addError(new FieldError("user", "companyName", "Company Name is required."));
        }
        if (user.getContactNumber() == null || user.getContactNumber().isEmpty()) {
            result.addError(new FieldError("user", "contactNumber", "Contact Number is required."));
        }

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            model.addAttribute("user", userService.getLoggedInUser());
            model.addAttribute("success", "");
            model.addAttribute("error", "");
            return "profile";
        }

        boolean isUpdated = userService.updateUserProfile(user);
        if (isUpdated) {
            model.addAttribute("success", "Profile updated successfully.");
        } else {
            model.addAttribute("error", "Failed to update profile. Please try again later.");
        }
        model.addAttribute("user", userService.getLoggedInUser());
        model.addAttribute("errors", new HashMap<String, String>());
        return "profile";
    }

}
