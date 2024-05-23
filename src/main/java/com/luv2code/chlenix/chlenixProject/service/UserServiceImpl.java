package com.luv2code.chlenix.chlenixProject.service;

import com.luv2code.chlenix.chlenixProject.dto.UserDto;
import com.luv2code.chlenix.chlenixProject.model.User;
import com.luv2code.chlenix.chlenixProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById((long) theId);
    }


}