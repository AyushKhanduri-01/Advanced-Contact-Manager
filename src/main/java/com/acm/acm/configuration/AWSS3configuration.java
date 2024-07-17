package com.acm.acm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class AWSS3configuration {
    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

   @Bean
    public AmazonS3 client(){

      System.out.println("process reached at Amazons3");

      System.out.println(awsAccessKey  +"   aws Access key");
      System.out.println(awsSecretKey  +"   aws Secret key");
      
     AWSCredentials awsCredentials= new BasicAWSCredentials(awsAccessKey, awsSecretKey);
     AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                          .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                          .withRegion(region)
                          .build();

        
      return amazonS3;
    }
}
