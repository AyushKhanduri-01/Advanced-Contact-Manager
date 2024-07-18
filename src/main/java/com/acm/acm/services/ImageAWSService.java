package com.acm.acm.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageAWSService {
  
    public String uploadImage( MultipartFile file);

    public String getImage(String imageName);
}
