package com.acm.acm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMethod;

import com.acm.acm.entity.User;
import com.acm.acm.forms.RegisterForm;
import com.acm.acm.services.UserService;



@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("working");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
       return "about";
    }

    @RequestMapping("/service")   
    public String service(Model model) {
        return "services";
    }

    @RequestMapping("/login")   
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/signup")   
    public String signup(Model model) {
        RegisterForm registerForm = new RegisterForm();
         model.addAttribute("registerForm", registerForm);
        return "signup";
    }

   
    @RequestMapping(value = "/registerUser", method=RequestMethod.POST)
    public String regesterUser(@ModelAttribute RegisterForm registerForm){
      User user = User.builder()
                   .name(registerForm.getName())
                   .email(registerForm.getEmail())
                   .password(registerForm.getPassword())
                   .phoneNumber(registerForm.getPhoneNumber())
                   .about(registerForm.getAbout())
                   .profilePic("https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg")
                   .build();
        userService.saveUser(user);

        // 
        System.out.println("user saved");
        return "redirect:/login";
    }
}
    
    
    

