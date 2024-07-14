package com.acm.acm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
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
    private boolean favorite= false;
    private String link;
    

    @ManyToOne
    private User adminUser;

   

    
}
