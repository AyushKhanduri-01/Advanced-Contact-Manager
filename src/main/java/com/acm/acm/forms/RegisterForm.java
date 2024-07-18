package com.acm.acm.forms;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

//! user to fetch value from signup form
public class RegisterForm {
 
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private String about;
  private MultipartFile pictureFile;
}
