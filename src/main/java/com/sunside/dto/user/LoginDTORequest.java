package com.sunside.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDTORequest (
        @Schema(description = "Username do usuário", example = "teste")
        @NotNull(message = "Username não pode ser nulo.")
        @NotBlank(message = "Username não pode ser vazio.")
        String username,

        @Schema(description = "Senha do usuário", example = "Teste@123")
        @NotNull(message = "Password não pode ser nulo.")
        @NotBlank(message = "Password não pode ser vazio.")
        String password
){
}

