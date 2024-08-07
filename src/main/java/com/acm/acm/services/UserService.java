package com.acm.acm.services;

import java.util.List;
import java.util.Optional;

import com.acm.acm.entity.Contact;
import com.acm.acm.entity.User;

public interface UserService {
    //! Optional will return User if present otherwise return null ( it prevent fron nullPointerException)
    Optional<User> saveUser(User user);

    Optional<User> getUserById(int userId);

    Optional<User> updateUser(User user);

    void deleteUser(int userId);

    boolean isUserExist(int userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    List<Contact> getContactList(int id);

    User getUserByEmail(String email);

    
}
