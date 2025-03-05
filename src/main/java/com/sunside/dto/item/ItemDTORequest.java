package com.sunside.dto.user;

public record ItemDTORequest(

        String name,
        String syllables,
        String image,
        String video,
        String category,
        String subcategory
) {
}
