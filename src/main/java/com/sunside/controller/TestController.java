package com.sunside.controller;

import com.sunside.utils.AWSUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/test")
@RequiredArgsConstructor
public class TestController {

    private final AWSUtils awsUtils;

    @GetMapping
    ResponseEntity<String> saveFile(){
        // return ResponseEntity.status(HttpStatus.CREATED).body(awsUtils.saveFile(file.getName(), file));
        return ResponseEntity.ok().build();
    }


}
