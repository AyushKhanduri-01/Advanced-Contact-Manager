package com.acm.acm.forms;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ContactForm {

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String description;
    private String link;
    private boolean favorite;
    private MultipartFile pictureFile;

}
