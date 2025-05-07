package com.sunside.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(url="${aiclient.url")
public interface AIClient {

    @PostMapping("classificacao")

    String searchCategory(MultipartFile image);
}
