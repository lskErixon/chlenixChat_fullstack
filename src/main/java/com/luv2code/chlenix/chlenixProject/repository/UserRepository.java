package com.luv2code.chlenix.chlenixProject.repository;

import com.luv2code.chlenix.chlenixProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail (String email);
}