package com.acm.acm.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acm.acm.entity.Contact;
import com.acm.acm.entity.User;
import com.acm.acm.forms.ContactForm;
import com.acm.acm.helper.GetLoggedInUser;
import com.acm.acm.services.ContactService;
import com.acm.acm.services.UserService;




@Controller
@RequestMapping("/user")
public class UserController {



private GetLoggedInUser getLoggedInUser;

@Autowired
private UserService userService;

@Autowired
private ContactService contactService;

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
@RequestMapping(value = "/addContact")
public String addContact(Model model){
    ContactForm contactForm = new ContactForm();
    model.addAttribute("contactForm", contactForm);
    return "userData/addContact";
}



@RequestMapping(value="/addContactData")
public String addContactData(@ModelAttribute ContactForm contactForm,Authentication authentication){



   String email = getLoggedInUser.getLoggedInUser(authentication);
   User user = userService.getUserByEmail(email);

   

   Contact contact = new Contact();
   contact.setName(contactForm.getName());
   contact.setEmail(contactForm.getEmail());
   contact.setPhoneNumber(contactForm.getPhoneNumber());
   contact.setAddress(contactForm.getAddress());
   contact.setPicture(contactForm.getPictureFile().getOriginalFilename());
   contact.setDescription(contactForm.getDescription());
   contact.setFavorite(contactForm.isFavorite());
   contact.setLink(contactForm.getLink());
   contact.setAdminUser(user);

  

     Optional<Contact> contact2 = contactService.saveContact(contact);
     if(contact2.isPresent()){
        return "userData/dashboard";
     }
     else{
        return "userData/addContact";
     }

    


}
//delete contact


//Update contact


//search contact

//view contact


}
