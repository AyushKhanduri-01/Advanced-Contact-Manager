package com.acm.acm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.acm.acm.entity.User;
import com.acm.acm.helper.GetLoggedInUser;
import com.acm.acm.services.UserService;
import org.springframework.ui.Model;

//!This file give info of loggedin User
@ControllerAdvice
public class RootController {

  @Autowired
  private UserService userService;

  @ModelAttribute
  public void getRoot(Model model, Authentication authentication) {
    if (authentication == null) {
      return;
    }


    //! GetLoggedInUser will return the loggedIn user details present in helper package
    String email = GetLoggedInUser.getLoggedInUser(authentication);
    User loggedInUser = userService.getUserByEmail(email);
    model.addAttribute("loggedInUser", loggedInUser);

  }
}
