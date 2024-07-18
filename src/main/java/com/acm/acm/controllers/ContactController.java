package com.acm.acm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.acm.acm.entity.Contact;
import com.acm.acm.entity.User;
import com.acm.acm.forms.ContactForm;
import com.acm.acm.helper.GetLoggedInUser;
import com.acm.acm.services.ContactService;
import com.acm.acm.services.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
//! Mange Contact related endpoint
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    private GetLoggedInUser getLoggedInUser;

    @Autowired
    private UserService userService;

    // !Change Favorite value of contact
    @RequestMapping(value = "/changeFav/{contactId}")
    public String changeFav(@PathVariable int contactId) {

        Contact contact = contactService.getById(contactId);
        contact.setFavorite(!contact.isFavorite());
        contactService.saveContact(contact);
        return "redirect:/user/allContacts";
    }

    // !delete contact
    @RequestMapping(value = "/delete/{contactId}")
    public String delete(@PathVariable int contactId) {

        contactService.deleteContactById(contactId);
        return "redirect:/user/allContacts";
    }

    // !Update contact
    @RequestMapping(value = "/update/{contactId}")
    public String updateContact(@PathVariable int contactId, Model model) {
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

    // ! Search Contact by name, email, phoneNumber
    @RequestMapping(value = "/search")
    private String searchContact(@RequestParam("searchType") String searchType,
            @RequestParam("searchValue") String searchValue, Model model, Authentication authentication) {

        String email = getLoggedInUser.getLoggedInUser(authentication);
        User user = userService.getUserByEmail(email);

        List<Contact> list = new ArrayList<>();
        if ("name".equals(searchType)) {
            list = contactService.getUserByName(searchValue);
        } else if ("phone".equals(searchType)) {
            list = contactService.getUserByPhone(searchValue);
        } else if ("email".equals(searchType)) {
            list = contactService.getUserByEmail(searchValue);
        }
 
        model.addAttribute("contactList", list);
        return "userData/contacts";
    }

}
