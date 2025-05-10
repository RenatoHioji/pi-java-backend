package com.sunside.dto.user;

import java.util.UUID;

public record LoginDTOResponse(
        String accessToken,
        String tokenType,
        UUID id,
        String username
) {

}
