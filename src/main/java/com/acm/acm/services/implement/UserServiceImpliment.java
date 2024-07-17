package com.acm.acm.services.implement;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.acm.acm.entity.Contact;
import com.acm.acm.entity.User;
import com.acm.acm.helper.UserException;
import com.acm.acm.repositories.UserRepository;
import com.acm.acm.services.UserService;

@Service
public class UserServiceImpliment implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> saveUser(User user) {

        System.out.println("Reacted to saveUser function");

        try{
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           System.out.println("no error in to saveUser function");
           //user.setRoleList(List.of("USER"));
            User tempUser = userRepository.save(user);
            System.out.println("no error in to User Repositoty saveUser function");
            return Optional.of(tempUser);
        }
        catch(Exception e){
          e.printStackTrace();
            return Optional.empty();
        }      
       
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
       Optional<User> user2 = userRepository.findById(user.getUserId());
       if(user2.isPresent()) {
            User newuser = user2.get();
            newuser.setName(user.getName());
            newuser.setPassword(user.getPassword());
            newuser.setAbout(user.getAbout());
            newuser.setContact(user.getContact());
            newuser.setEmail(user.getEmail());
            newuser.setProfilePic(user.getProfilePic());
            newuser.setPhoneNumber(user.getPhoneNumber());
            newuser.setEnabled(user.isEnabled());
            newuser.setEmailVarified(user.isEmailVarified());
            newuser.setPhoneVarified(user.isPhoneVarified());
         User upatUser = userRepository.save(newuser);
         return Optional.ofNullable(upatUser);
       }
       else{
        throw new UserException("User not found with given information.");
       }
        
    }

    @Override
    public void deleteUser(int id) {
      /* 
        Optional<User> temUser  = userRepository.findById(id);
       if(temUser.isPresent()){
         userRepository.delete(temUser.get());
       }      
       else{
        throw new UserException("User not found");
       } 
       */
     User tempUser = userRepository.findById(id)
     .orElseThrow(() -> new UserException("User not found with provided information "));
       userRepository.delete(tempUser);

    }

    @Override
    public boolean isUserExist(int userId) {
        User temUser =  userRepository.findById(userId).orElse(null);
        return (temUser==null)?false:true;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User temUser =  userRepository.findByEmail(email).orElse(null);
        return (temUser==null)?false:true;
    }

    

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
       
    }

    @Override
    public User getUserByEmail(String email) {
      return userRepository.findByEmail(email).orElse(null);
    
    }

    @Override
    public List<Contact> getContactList(int id) {
       User user = userRepository.findById(id).orElse(null);
       if(user != null){
        return user.getContact();
       }
       else{
        throw new UserException("User not found with provided information ");
        }
    }

   


    
    

}
