package com.sunside.controller;

import com.sunside.dto.FileDTORequest;
import com.sunside.utils.AWSUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/test")
@RequiredArgsConstructor
public class TestController {

    private final AWSUtils awsUtils;

    @PostMapping
    ResponseEntity<String> saveFile(
            @Valid @RequestPart("request") FileDTORequest fileDTORequest
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(awsUtils.saveFile(fileDTORequest.file().getName(), fileDTORequest.file()));
    }


}
