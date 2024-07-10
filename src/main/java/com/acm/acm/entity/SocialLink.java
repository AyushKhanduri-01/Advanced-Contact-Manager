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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SocialLink {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int linkId;
    private String link;
    private String title;


    @ManyToOne
    private Contact contact;

}
