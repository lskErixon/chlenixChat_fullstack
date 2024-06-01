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

    /**
     * The method handles POST requests to the /registration URL.
     * It binds form data to a UserDto object.
     * It saves the user data via userService.
     * It adds a success message to the model.
     * It returns the view name for the login page, typically navigating the user to the login screen after successful registration.
     */

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

    /**
     * The method handles GET requests to the /chat URL.
     * It retrieves the details of the currently authenticated user using the Principal object.
     * It adds the UserDetails object to the model under the attribute name "user".
     * It returns the view name for the chat page, typically navigating the user
     * to the chat screen where they can see their user details.
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/chat")
    public String userPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "/chat";
    }

    /**
     * allows displaying the list of users
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("admin-page")
    public String adminPage (Model model, Principal principal) {
        List<User> theUser = userService.getAllUsers();
        model.addAttribute("user", theUser);
        return "admin-page";
    }

    /**
     * allow delete some user
     * @param theId
     * @return
     */
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId){
        userService.deleteById(theId);
        return "redirect:/admin-page";
    }
}