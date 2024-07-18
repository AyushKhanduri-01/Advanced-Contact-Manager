package com.acm.acm.repositories;

import org.springframework.stereotype.Repository;

import com.acm.acm.entity.Contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

    List<Contact> findByNameContaining(String searchValue);

    List<Contact> findByEmailContaining(String searchValue);

    List<Contact> findByPhoneNumberContaining(String searchValue);
 
}
