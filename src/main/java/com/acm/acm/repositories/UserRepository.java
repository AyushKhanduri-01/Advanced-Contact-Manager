package com.acm.acm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acm.acm.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    //can add custom query 
    //springboot automatically implement funtion.--> method must be in this formater--> findBy<Filedname> 
    Optional<User>findByEmail(String email);
    Optional<User>findByEmailAndPassword(String email, String password);


}
