package com.luv2code.chlenix.chlenixProject.service;

import com.luv2code.chlenix.chlenixProject.dto.UserDto;
import com.luv2code.chlenix.chlenixProject.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User save(UserDto userDto);
    List<User> getAllUsers();
    void deleteById(int theId);

}