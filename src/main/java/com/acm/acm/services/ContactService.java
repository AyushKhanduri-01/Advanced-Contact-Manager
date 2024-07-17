package com.acm.acm.services;


import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.acm.acm.entity.Contact;

@Service
public interface ContactService {

 Optional<Contact> saveContact(Contact contact); 

 void deleteContact(Contact contact);

 void deleteContactById(int id);

 Contact updateContact(Contact contact);

 List<Contact> getAllContact(int id);

  Contact getById(int id);

  List<Contact> search(String name, String email, String phoneNumber);
    
    
    
    
}
