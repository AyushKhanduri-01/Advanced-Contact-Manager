package com.acm.acm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.acm.acm.entity.Contact;
import com.acm.acm.entity.User;
import com.acm.acm.forms.ContactForm;
import com.acm.acm.helper.GetLoggedInUser;
import com.acm.acm.services.ContactService;
import com.acm.acm.services.ImageAWSService;
import com.acm.acm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

   private GetLoggedInUser getLoggedInUser;

   @Autowired
   private UserService userService;

   @Autowired
   private ContactService contactService;

   @Autowired
   private ImageAWSService awsService;

   // for dashboard
   @RequestMapping(value = "/dashboard")
   public String userDashboard(Model model) {

      return "redirect:/user/allContacts";
      // return "userData/contacts";
   }


   @RequestMapping(value = "/profile")
   public String userProfile(Authentication authentication) {
      return "userData/profile";
   }



   // add contact
   @RequestMapping(value = "/addContact")
   public String addContact(Model model) {
      ContactForm contactForm = new ContactForm();
      model.addAttribute("contactForm", contactForm);
      return "userData/addContact";
   }

   // add new contactData
   @RequestMapping(value = "/addContactData/{contactId}")
   public String addContactData(@ModelAttribute ContactForm contactForm, @PathVariable int contactId,
         Authentication authentication) {

      String email = getLoggedInUser.getLoggedInUser(authentication);
      User user = userService.getUserByEmail(email);

      if (contactId != -1) {

         Contact currContact = contactService.getById(contactId);
         System.out.println("updatting the contact");
         System.out.println("id is : " + contactForm.getContactId());
         MultipartFile file = contactForm.getPictureFile();
         System.out.println(file);
         System.out.println(file.getOriginalFilename());
         String fileName = awsService.uploadImage(file);

         currContact.setName(contactForm.getName());
         currContact.setEmail(contactForm.getEmail());
         currContact.setPhoneNumber(contactForm.getPhoneNumber());
         currContact.setAddress(contactForm.getAddress());
         currContact.setDescription(contactForm.getDescription());
         currContact.setFavorite(contactForm.isFavorite());
         currContact.setLink(contactForm.getLink());
         currContact.setAdminUser(user);
         currContact.setPicture(fileName);

         Optional<Contact> contact2 = contactService.saveContact(currContact);

         if (contact2.isPresent()) {
            return "userData/dashboard";
         } else {
            return "userData/addContact";
         }

      } else {
         // processing image and uplode to aws
         System.out.println("adding new contact");
         System.out.println("id is : " + contactForm.getContactId());
         MultipartFile file = contactForm.getPictureFile();
         System.out.println(file);
         System.out.println(file.getOriginalFilename());
         String fileName = awsService.uploadImage(file);

         // processing image and uplode to aws end
         // System.out.println("file name return by aws function : " + fileName);

         Contact contact = new Contact();
         contact.setName(contactForm.getName());
         contact.setEmail(contactForm.getEmail());
         contact.setPhoneNumber(contactForm.getPhoneNumber());
         contact.setAddress(contactForm.getAddress());
         contact.setDescription(contactForm.getDescription());
         contact.setFavorite(contactForm.isFavorite());
         contact.setLink(contactForm.getLink());
         contact.setAdminUser(user);
         contact.setPicture(fileName);

         Optional<Contact> contact2 = contactService.saveContact(contact);

         if (contact2.isPresent()) {
            return "userData/dashboard";
         } else {
            return "userData/addContact";
         }

      }

      // return "userData/addContact";

   }
   // delete contact

   // Update contact

   // search contact

   // View all contact
   @RequestMapping(value = "/allContacts")
   public String viewAllContacts(Model model, Authentication authentication) {
      String email = getLoggedInUser.getLoggedInUser(authentication);
      User user = userService.getUserByEmail(email);

      int id = user.getUserId();
      List<Contact> contactList = contactService.getAllContact(id);

      model.addAttribute("contactList", contactList);

      return "userData/contacts";
   }

}
