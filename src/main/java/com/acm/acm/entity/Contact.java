package com.acm.acm.entity;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact {
 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contactId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;

    @Column(length = 1000)
    private String note;
    private boolean favorite= false;
    private String link;
    

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER , orphanRemoval = true)                              
    private List<SocialLink> links = new ArrayList<>();


    
}
