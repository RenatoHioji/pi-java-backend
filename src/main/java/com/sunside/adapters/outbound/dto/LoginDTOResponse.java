package com.sunside.adapters.outbound.dto;

import java.util.UUID;

public record LoginDTOResponse(
        String accessToken,
        String tokenType,
        UUID id,
        String username
) {

}
