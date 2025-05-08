package com.sunside.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name="AI-CLIENT", url="${aiclient.url}")
public interface AIClient {

    @PostMapping("classificacao")
    String searchCategory(MultipartFile image);
}
