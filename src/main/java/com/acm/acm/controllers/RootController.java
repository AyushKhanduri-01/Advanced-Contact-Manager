package com.acm.acm.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.acm.acm.entity.User;
import com.acm.acm.helper.GetLoggedInUser;
import com.acm.acm.services.UserService;

import org.springframework.ui.Model;


//This file will run every time whenever any endpoint get called.
@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void getRoot(Model model, Authentication authentication) {
       if(authentication == null){
       
        return;
       }
    
       String email = GetLoggedInUser.getLoggedInUser(authentication);
       User loggedInUser=userService.getUserByEmail(email);

         model.addAttribute("loggedInUser",loggedInUser);
    


    }
}
