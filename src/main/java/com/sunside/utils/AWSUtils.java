package com.sunside.utils;

import com.sunside.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedFileUpload;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class AWSUtils {


    private final S3AsyncClient s3Client;

    private final S3TransferManager transferManager;

    @Value("${aws.bucket}")
    private String bucketName;

    public String saveFile(String key, MultipartFile multipartFile){
        try{
            Path tempFilePath = Files.createTempFile(multipartFile.getOriginalFilename(), ".tmp");
            multipartFile.transferTo(tempFilePath.toFile());
            UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                    .putObjectRequest(PutObjectRequest.builder()
                            .key(key)
                            .bucket(bucketName)
                            .build())
                    .source(tempFilePath)
                    .build();

            FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);

            CompletedFileUpload uploadResult = fileUpload.completionFuture().join();

            return uploadResult.response().eTag();
        }catch (Exception e){
            log.error("Erro ao salvar item com erro: {}", e.getMessage());
            throw new BusinessException("Não foi possível salvar item com erro");
        }
    }

    private File convertToFile(String key, MultipartFile multipartFile) {
        try {
            File temp = File.createTempFile(key, null);
            multipartFile.transferTo(temp);
            return temp;
        } catch (Exception e) {
            throw new BusinessException("Não foi possível transformar o item em arquivo.");
        }

    }

    public void deleteFile(String key){
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
