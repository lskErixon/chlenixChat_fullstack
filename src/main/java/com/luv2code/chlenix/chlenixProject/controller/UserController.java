package com.luv2code.chlenix.chlenixProject.controller;

import com.luv2code.chlenix.chlenixProject.dto.UserDto;
import com.luv2code.chlenix.chlenixProject.model.User;
import com.luv2code.chlenix.chlenixProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
        return "registration-form";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfuly!");
        return "/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/chat")
    public String userPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "/chat";
    }

    @GetMapping("admin-page")
    public String adminPage (Model model, Principal principal) {
        List<User> theUser = userService.getAllUsers();
        model.addAttribute("user", theUser);
        return "admin-page";
    }
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId){
        userService.deleteById(theId);
        return "redirect:/admin-page";
    }
}