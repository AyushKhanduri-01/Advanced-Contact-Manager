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
import org.springframework.web.multipart.MultipartFile;
import com.acm.acm.entity.Contact;
import com.acm.acm.entity.User;
import com.acm.acm.forms.ContactForm;
import com.acm.acm.forms.RegisterForm;
import com.acm.acm.helper.GetLoggedInUser;
import com.acm.acm.services.ContactService;
import com.acm.acm.services.ImageAWSService;
import com.acm.acm.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
//! Manage all end points related to user
@RequestMapping("/user")
public class UserController {

   private GetLoggedInUser getLoggedInUser;

   @Autowired
   private UserService userService;

   @Autowired
   private ContactService contactService;

   @Autowired
   private ImageAWSService awsService;


    @Autowired
    private HttpSession httpSession;


   //! for dashboard
   @RequestMapping(value = "/dashboard")
   public String userDashboard(Model model) {
       return "redirect:/user/allContacts";
   }

   //!for profile
   @RequestMapping(value = "/profile")
   public String userProfile(Authentication authentication, Model  model) {

       String email = getLoggedInUser.getLoggedInUser(authentication);
       User user = userService.getUserByEmail(email);
       model.addAttribute("user", user);
       return "userData/profile";
   }



   //!Edit profile 
   @RequestMapping(value = "/editProfile")
   public String editProfile(Authentication authentication, Model model){
    String email = getLoggedInUser.getLoggedInUser(authentication);
    User user = userService.getUserByEmail(email);
   
    RegisterForm userData = new RegisterForm();

    userData.setName(user.getName());
    userData.setEmail(user.getEmail());
    userData.setPhoneNumber(user.getPhoneNumber());
    userData.setAbout(user.getAbout());
    userData.setPassword("Add new Password");
    userData.setPictureFile(null);

    model.addAttribute("user", user);
    model.addAttribute("userData", userData);

      return "editProfile";
   }


   //!Update edited value in profile
   @RequestMapping(value="/editUserProfile/{userId}")
   private String editUserProfile(@PathVariable int userId, @ModelAttribute RegisterForm userData){

    Optional<User> tempUser = userService.getUserById(userId);
    User user = tempUser.get();

    user.setName(userData.getName());
    user.setEmail(userData.getEmail());
    user.setAbout(userData.getAbout());
    user.setPhoneNumber(userData.getPhoneNumber());

    if(userData.getPassword() != null && !userData.getPassword().isEmpty() && !userData.getPassword().equals("Add new Password")){
      user.setPassword(userData.getPassword());
    }
    
    String image = awsService.uploadImage(userData.getPictureFile());
    user.setProfilePic(image);



    Optional<User> tempUser2 =  userService.saveUser(user);
    if(tempUser2.isPresent()){
      return "redirect:/user/profile";        
    }
    else{
      return "redirect:/user/profile"; 
    }
      


      
   }


   //!Delete user
@RequestMapping(value = "/deleteUser/{userId}")
 private String deleteUser(@PathVariable int userId){
   userService.deleteUser(userId);
   httpSession.invalidate();
   return "redirect:/login";
}


   //! add contact button
   @RequestMapping(value = "/addContact")
   public String addContact(Model model) {
      ContactForm contactForm = new ContactForm();
      model.addAttribute("contactForm", contactForm);
      return "userData/addContact";
   }


   //! add new contact to database
   @RequestMapping(value = "/addContactData/{contactId}")
   public String addContactData(@ModelAttribute ContactForm contactForm, @PathVariable int contactId,
         Authentication authentication) {

      String email = getLoggedInUser.getLoggedInUser(authentication);
      User user = userService.getUserByEmail(email);

      Contact currContact = contactService.getById(contactId);
   
      if(currContact==null){
        
         MultipartFile file = contactForm.getPictureFile();
         String fileName = awsService.uploadImage(file);

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
            return "redirect:/user/allContacts";
         } else {
            return "redirect:/user/allContacts";
         }
      }
      else{           
            MultipartFile file = contactForm.getPictureFile();
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
               return "redirect:/user/allContacts";
            } else {
               return "redirect:/user/allContacts";
            }
      }

   }

   //! get all contacts 
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
