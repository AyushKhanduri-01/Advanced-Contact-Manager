package com.acm.acm.services.implement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.acm.acm.services.ImageAWSService;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.HttpMethod;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.amazonaws.services.s3.AmazonS3;

@Service
public class ImageAWSServiceImplement implements ImageAWSService {

    @Autowired
    private AmazonS3 aS3;

    @Value("${cloud.aws.s3.bucket}")
    private String aws_bucket;

    @Override
    public String uploadImage(MultipartFile file) {

       
        System.out.println("process reached at aws uplodeImage functin ");

        String fileName = file.getOriginalFilename();
        String uplodeFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {

            System.out.println("bucket name : " + aws_bucket);
          PutObjectResult result = aS3.putObject(new PutObjectRequest(aws_bucket,uplodeFileName,file.getInputStream(),metadata));
          String url = getImage(uplodeFileName);
          System.out.println("file uploded succesfully" + result);
          return url;
          
        } catch (Exception e) {
            System.out.println("exception on uploding file" + e.toString());
            e.printStackTrace();
        }

        return "null";

    }

    @Override
    public String getImage(String imageName) {

        try {

            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 60 * 24 *7; // 10 years
            expiration.setTime(expTimeMillis);

            
            String encodedImageName = URLEncoder.encode(imageName, StandardCharsets.UTF_8.toString());
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(aws_bucket,encodedImageName)
                                 .withMethod(HttpMethod.GET)
                                 .withExpiration(expiration);
            
            URL url = aS3.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
           
        } catch (Exception e) {
            System.out.println("error in reterving file "+ e.toString());
            throw new RuntimeException("Failed to retrieve file from S3", e);
        }
        
    }

}
