package com.acm.acm.services;

import java.util.List;
import java.util.Optional;

import com.acm.acm.entity.User;

public interface UserService {
    Optional<User> saveUser(User user);

    Optional<User> getUserById(int userId);

    Optional<User> updateUser(User user);

    void deleteUser(int userId);

    boolean isUserExist(int userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    // add more methods here related user service[logic]
}
