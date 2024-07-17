package com.acm.acm.configuration;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.acm.acm.entity.User;
import com.acm.acm.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

   

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
       
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

            OAuth2User oAuth2User = token.getPrincipal();
            String name = oAuth2User.getAttribute("name");
            String email = oAuth2User.getAttribute("email");
            String picture = oAuth2User.getAttribute("picture");
            

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setAbout("Login by google.");
            user.setPassword(email);
            user.setProfilePic(picture);
          //  user.setRoleList(List.of("USER"));



            User tempUser = userRepository.findByEmail(email).orElse(null);
            if (tempUser == null) {
                userRepository.save(user);
            }
     
    
    
    new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
}
}
