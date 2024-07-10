package com.acm.acm.forms;

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
public class RegisterForm {
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private String about;
}
