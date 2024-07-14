package com.acm.acm.repositories;

import org.springframework.stereotype.Repository;

import com.acm.acm.entity.Contact;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
 
}
