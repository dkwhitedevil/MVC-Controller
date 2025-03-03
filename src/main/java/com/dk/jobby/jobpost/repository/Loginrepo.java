package com.dk.jobby.jobpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dk.jobby.jobpost.domain.Login;

@Repository
public interface Loginrepo extends JpaRepository<Login, String> {
    Login findByUsernameAndPassword(String username, String password);
}
