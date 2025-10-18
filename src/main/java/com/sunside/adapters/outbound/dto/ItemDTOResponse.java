package com.sunside.adapters.outbound.dto;

import java.util.UUID;

public record ItemDTOResponse(
        UUID id,
        String name,
        String syllables,
        String image,
        String video,
        String category,
        String subcategory,
        UUID userId
) {
}
