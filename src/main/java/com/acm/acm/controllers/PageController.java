package com.acm.acm.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMethod;

import com.acm.acm.entity.User;
import com.acm.acm.forms.RegisterForm;
import com.acm.acm.services.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String entry(){
        System.out.println("working");
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        //System.out.println("working");
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
    public String regesterUser(@ModelAttribute RegisterForm registerForm,HttpSession session){

  
      User user2 = new User();
      user2.setName(registerForm.getName());
      user2.setEmail(registerForm.getEmail());
      user2.setPassword(registerForm.getPassword());
      user2.setPhoneNumber(registerForm.getPhoneNumber());
      user2.setAbout(registerForm.getAbout());
      user2.setProfilePic("https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg");
    

      Optional<User> tempUser =  userService.saveUser(user2);
      if(tempUser.isPresent()){
        System.out.println("user saved");
        session.setAttribute("messageSuccess","User Regestered succesfully");
        return "redirect:/login";        
      }
      else{
        System.out.println("user not saved");
        session.setAttribute("messageFail","User Regestered Failed! Try again");
        return "redirect:/signup";
      }
        
        
    }
}
    
    
    

