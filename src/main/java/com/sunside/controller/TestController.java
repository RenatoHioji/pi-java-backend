package com.sunside.controller;

import com.sunside.utils.AWSUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;

@Controller
@RequestMapping("v1/test")
@RequiredArgsConstructor
public class TestController {

    private final AWSUtils awsUtils;
    @PostMapping
    ResponseEntity<String> saveFile(){
        // return ResponseEntity.status(HttpStatus.CREATED).body(awsUtils.saveFile(file.getName(), file));
        return ResponseEntity.ok().build();
    }


}
