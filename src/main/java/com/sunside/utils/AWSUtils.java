package com.sunside.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProcessCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedFileUpload;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import java.net.URI;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AWSUtils {

    private final S3AsyncClient s3Client;

    private final S3TransferManager transferManager;

    public String saveFile(String bucketName, String key, URI filePathURI){
        try{
            UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                    .putObjectRequest(b -> b.bucket(bucketName).key(key))
                    .source(Paths.get(filePathURI))
                    .build();

            FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);

            CompletedFileUpload uploadResult = fileUpload.completionFuture().join();

            return uploadResult.response().eTag();
        }catch (Exception e){
            // Do something when error on save
            return null;
        }
    }

    public void deleteFile(String bucketName, String key){
            try{
                DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();
                s3Client.deleteObject(deleteObjectRequest);
            }catch (Exception e){
                //Do something
            }
    }
}
