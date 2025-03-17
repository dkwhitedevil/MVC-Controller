package com.dk.jobby.jobpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dk.jobby.jobpost.domain.User;
import com.dk.jobby.jobpost.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // Show login/register page
    @GetMapping("/")
    public String showLoginPage(@RequestParam(name = "success", required = false) String success,
                                @RequestParam(name = "error", required = false) String error,
                                Model model) {
        if (success != null) {
            model.addAttribute("success", success);
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    // Process login
    @PostMapping("/login")
public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
    User user = userService.authenticate(username, password);

    if (user != null) {
        return "redirect:/home"; // Redirect to home if login is successful
    } else {
        return "redirect:/?error=Invalid username or password"; // Redirect with an error message
    }
}


    // Show home page
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    // Show register form (on same login page)
    @PostMapping("/register")
    public String processRegister(@RequestParam String username, 
                                  @RequestParam String password, 
                                  @RequestParam String email, 
                                  Model model) {
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("error", "Username already exists!");
            return "login";
        }

        User newUser = new User(username, password, email);
        userService.saveUser(newUser);

        return "redirect:/?success=Registration successful! Please log in.";
    }
}
