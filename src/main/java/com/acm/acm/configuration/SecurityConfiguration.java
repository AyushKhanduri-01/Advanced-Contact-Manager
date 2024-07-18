package com.acm.acm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.acm.acm.services.UserSequrityService;

@Configuration
// This file is for Spring Security Configuration. Securing endpoints.
public class SecurityConfiguration {

  @Autowired
  private UserSequrityService userSequrityService;

  @Autowired
  private OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

  @Bean
  public AuthenticationProvider authenticationProvider() {
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

    http.formLogin(formLogin -> {
      formLogin.loginPage("/login");
      formLogin.loginProcessingUrl("/authenticate");
      formLogin.successForwardUrl("/user/profile");
      formLogin.failureForwardUrl("/login");
      formLogin.usernameParameter("email");
      formLogin.passwordParameter("password");

    });

    http.oauth2Login(loginForm -> {
      loginForm.loginPage("/login");
      loginForm.successHandler(oAuthAuthenticationSuccessHandler);

    });

    http.csrf(AbstractHttpConfigurer::disable);
    http.logout(logoutForm -> {
      logoutForm.logoutUrl("/logout");
      logoutForm.logoutSuccessUrl("/login");

    });
    
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
