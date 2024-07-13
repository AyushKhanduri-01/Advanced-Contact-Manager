package com.acm.acm.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {



//for dashboard
@RequestMapping(value = "/dashboard")
public String userDashboard(Model model) {   
    
    return "userData/dashboard";
}

@RequestMapping(value = "/profile")
public String userProfile(Authentication authentication) {
   return "userData/profile";
}


//add contact


//delete contact


//Update contact


//search contact

//view contact


}
