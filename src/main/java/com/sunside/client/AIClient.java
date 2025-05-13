package com.sunside.client;

import com.sunside.configuration.FeignConfig;
import com.sunside.dto.item.CategoryDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name="AI-CLIENT", url="${aiclient.url}", configuration = FeignConfig.class)
public interface AIClient {
;
    @PostMapping(path = "classificacao", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDTOResponse searchCategory(@RequestPart("file") MultipartFile file);
}
