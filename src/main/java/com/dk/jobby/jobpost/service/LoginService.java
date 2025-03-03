package com.dk.jobby.jobpost.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dk.jobby.jobpost.domain.Login;
import com.dk.jobby.jobpost.repository.Loginrepo;

@Service
public class LoginService {

    @Autowired
    private Loginrepo rep;

    public Login log(String username, String password) {
        Login user = rep.findByUsernameAndPassword(username, password);
        return user;
    }
}
