package com.acm.acm.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

//! return LoggedIn user data
public class GetLoggedInUser {
    public static String getLoggedInUser(Authentication authentication){
        String name=null;

        if(authentication instanceof  OAuth2AuthenticationToken){
             OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

            OAuth2User oAuth2User = token.getPrincipal();
            name = oAuth2User.getAttribute("email");
        }
        else{
            name = authentication.getName();
        }

        return name;
        
    }

}
