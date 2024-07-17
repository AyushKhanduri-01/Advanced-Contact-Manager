package com.acm.acm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acm.acm.entity.Contact;
import com.acm.acm.forms.ContactForm;
import com.acm.acm.services.ContactService;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;




    @RequestMapping(value = "/changeFav/{contactId}")
    public String changeFav(@PathVariable int contactId){
     
        Contact contact = contactService.getById(contactId);
        contact.setFavorite(!contact.isFavorite());
        contactService.saveContact(contact);
        return "redirect:/user/allContacts";
    }



    @RequestMapping(value="/delete/{contactId}")
    public String delete(@PathVariable int contactId){
        
         contactService.deleteContactById(contactId);
         return "redirect:/user/allContacts";
    }



    @RequestMapping(value = "/update/{contactId}")
    public String updateContact(@PathVariable int contactId, Model model){
       Contact contact = contactService.getById(contactId);
       ContactForm contactForm = new ContactForm();
       contactForm.setContactId(contact.getContactId());
       contactForm.setName(contact.getName());
       contactForm.setPhoneNumber(contact.getPhoneNumber());
       contactForm.setAddress(contact.getAddress());
       contactForm.setEmail(contact.getEmail());
       contactForm.setFavorite(contact.isFavorite());
       contactForm.setLink(contact.getLink());
       contactForm.setDescription(contact.getDescription());
     

       model.addAttribute("contactForm", contactForm);
      
       return "userData/addContact";

    }

}
