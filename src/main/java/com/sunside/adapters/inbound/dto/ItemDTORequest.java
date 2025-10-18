package com.sunside.adapters.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ItemDTORequest(

        @Schema(description = "Nome do item", example = "Teste")
        String name,
        String syllables,

        @Schema(description = "Arquivo de imagem", type = "string", format = "binary")
        @NotNull(message = "Imagem não pode ser nula.")
        @NotBlank(message = "Image não pode estar vazia.")
        MultipartFile image,

        @Schema(description = "Arquivo de video", type = "string", format = "binary")
        @NotNull(message = "Imagem não pode ser nula.")
        @NotBlank(message = "Image não pode estar vazia.")
        MultipartFile video,

        @Schema(description = "Arquivo de audio", type = "string", format = "binary")
        @NotNull(message = "Imagem não pode ser nula.")
        @NotBlank(message = "Image não pode estar vazia.")
        MultipartFile audio,

        String category,

        String subcategory
) {
}
