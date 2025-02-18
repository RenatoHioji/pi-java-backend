package com.sunside.model;


import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public record FileDTORequest(
        @NotEmpty(message = "Arquivo não pode ser vazio")
        MultipartFile file
) {
}
