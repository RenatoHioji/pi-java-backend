package com.sunside.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

@Configuration
public class AWSClients {

    @Value("${aws.region}")
    private String REGION;

    @Value("${aws.accessKey}")
    private String ACCESS_KEY;

    @Value("${aws.secretKey}")
    private String SECRET_KEY;

    @Bean
    public S3AsyncClient s3Client() {
        return S3AsyncClient.builder()
                .region(Region.of(REGION))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
                .build();
    }

    @Bean
    public S3TransferManager s3TransferManager(){
        return S3TransferManager.builder()
                .s3Client(s3Client())
                .build();
    }
}
