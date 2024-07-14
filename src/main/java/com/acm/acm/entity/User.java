package com.acm.acm.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

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
    private boolean enabled = true;
    private boolean emailVarified = false;
    private boolean phoneVarified = false;

    // Mapping to contact table.
    @OneToMany(mappedBy = "adminUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        List<GrantedAuthority> roles = roleList.stream()
                        .map(role -> new SimpleGrantedAuthority(role.trim()))
                        .collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
