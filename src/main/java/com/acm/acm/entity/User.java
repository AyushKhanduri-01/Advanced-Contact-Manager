package com.acm.acm.entity;

import java.util.ArrayList;
import java.util.*;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(nullable = false)   
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String about;
    private String profilePic;

    @Column(unique = true)
    private String phoneNumber;
    private boolean enabled;
    private boolean emailVarified=false;
    private boolean phoneVarified=false;


    @Enumerated
    //signup source -- self or google or can add more
    private Providers provider = Providers.SELF;
    private String providerUserId;


    //Mapping to contact table.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();


    
}
