package com.acm.acm.configuration;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.acm.acm.services.UserSequrityService;

@Configuration
public class SecurityConfiguration {

@Autowired
 private UserSequrityService userSequrityService;

 @Bean
 public AuthenticationProvider authenticationProvider(){
  DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

  provider.setUserDetailsService(userSequrityService);
  provider.setPasswordEncoder(passwordEncoder());

  return provider;
 }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("user/**").authenticated();

                 authorize.anyRequest().permitAll();
              
        });    

        http.formLogin(org.springframework.security.config.Customizer.withDefaults());

        // ... other configurations
        return http.build();
    }


   

 @Bean
public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }






}
