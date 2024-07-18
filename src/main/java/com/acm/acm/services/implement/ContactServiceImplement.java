package com.acm.acm.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acm.acm.entity.Contact;
import com.acm.acm.repositories.ContactRepository;
import com.acm.acm.services.ContactService;
import com.acm.acm.services.UserService;


@Service
public class ContactServiceImplement implements ContactService{
  
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Contact> saveContact(Contact contact) {
        
      Contact contact2 = contactRepository.save(contact);
      return Optional.of(contact2);
           
    }


    @Override
    public void deleteContact(Contact contact) {
       
         contactRepository.delete(contact);     
    }


    @Override
    public Contact updateContact(Contact contact) {
       Optional<Contact> contact2 = contactRepository.findById(contact.getContactId());
       if(contact2.isPresent()) {
           Contact contactTemp = contact2.get();
           contactTemp.setName(contact.getName());
           contactTemp.setAddress(contact.getAddress());
           contactTemp.setDescription(contact.getDescription());
           contactTemp.setEmail(contact.getEmail());
           contactTemp.setLink(contact.getLink());
           contactTemp.setFavorite(contact.isFavorite());
           contactTemp.setAdminUser(contact.getAdminUser());
           contactTemp.setPicture(contact.getPicture());

           return contactRepository.save(contactTemp);
       }
       else{
        return null;
       }


       
    }


    @Override
    public List<Contact> getAllContact(int id) {
       
        return userService.getContactList(id);
    }


    @Override
    public Contact getById(int id) {
        
        return contactRepository.findById(id).orElse(null);
        
    }


    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return null;
    }


    @Override
    public void deleteContactById(int id) {
       contactRepository.deleteById(id);
    }


    @Override
    public List<Contact> getUserByName(String searchValue) {
        System.out.println("inside by name " + searchValue);
        return contactRepository.findByNameContaining(searchValue);
    }


    @Override
    public List<Contact> getUserByPhone(String searchValue) {
        System.out.println("inside by phone "  + searchValue);
         List <Contact> list = contactRepository.findByPhoneNumberContaining(searchValue);
         System.out.println(list.size());
         return list;
    }


    @Override
    public List<Contact> getUserByEmail(String searchValue) {
        System.out.println("inside by email "  + searchValue );
       return contactRepository.findByEmailContaining(searchValue);
    }

    
  
}
