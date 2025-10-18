package com.sunside.adapters.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDTORequest (
        @Schema(description = "Username do usuário", example = "teste")
        @NotNull(message = "Username não pode ser nulo.")
        @NotBlank(message = "Username não pode ser vazio.")
        String username,

        @Schema(description = "Senha do usuário", example = "Teste@123")
        @NotNull(message = "Password não pode ser nulo.")
        @NotBlank(message = "Password não pode ser vazio.")
        @Pattern(message = "Password deve ter no mínimo 8 caracteres, contendo 1 letra maiúscula, 1 letra minúscula, 1 caracter especial e 1 dígito.", regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$")
        String password
){
}
