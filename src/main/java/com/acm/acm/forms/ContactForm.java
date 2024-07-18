package com.acm.acm.forms;

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

//! Used to get value from AddContactform (we send obj of this pojo to form --> form will return obj of this pojo with updated / input value)
public class ContactForm {

    private int contactId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String description;
    private String link;
    private boolean favorite;
    private MultipartFile pictureFile;

}
