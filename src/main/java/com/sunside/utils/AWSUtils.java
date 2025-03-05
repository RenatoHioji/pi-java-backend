package com.sunside.utils;

import com.sunside.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedFileUpload;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import java.io.File;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class AWSUtils {

    private final S3AsyncClient s3Client;

    private final S3TransferManager transferManager;

    @Value("${aws.bucket}")
    private String bucketName;

    public String saveFile(String key, MultipartFile multipartFile){
        try{
            File file = this.convertToFile(multipartFile);
            UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                    .putObjectRequest(b -> b.bucket(bucketName).key(key))
                    .source(file)
                    .build();

            FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);

            CompletedFileUpload uploadResult = fileUpload.completionFuture().join();

            return uploadResult.response().eTag();
        }catch (Exception e){

            return null;
        }
    }

    private File convertToFile(MultipartFile multipartFile) {
        try {
            return Files.createTempFile("temp-", multipartFile.getOriginalFilename()).toFile();
        } catch (Exception e) {
            throw new BusinessException("Não foi possível transformar o item em arquivo.");
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
