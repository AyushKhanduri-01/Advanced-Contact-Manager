package com.acm.acm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {

//for dashboard
@RequestMapping(value = "/dashboard", method=RequestMethod.POST)
public String userDashboard(Model model) {
    
    return "userData/dashboard";
}



//user profile
@RequestMapping(value = "/profile", method=RequestMethod.GET)
public String userProfile(Model model) {
  
    return "userData/profile";
}  

//add contact


//delete contact


//Update contact


//search contact

//view contact


}
