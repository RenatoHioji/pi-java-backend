package com.sunside.mapper;

import com.sunside.dto.item.ItemDTORequest;
import com.sunside.model.Item;

import java.time.LocalDateTime;

public class ItemMapper {
    private ItemMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Item map(ItemDTORequest request){
        return Item
                .builder()
                .name(request.name())
                .image(request.image().getOriginalFilename() + LocalDateTime.now())
                .video(request.video().getOriginalFilename() + LocalDateTime.now())
                .audio(request.audio().getOriginalFilename() + LocalDateTime.now())
                .syllables(request.syllables())
                .category(request.category())
                .subcategory(request.subcategory())
                .build();
    }

    public static Item map(Item item, ItemDTORequest request){
        return
                Item.builder()
                        .id(item.getId())
                        .name(request.name())
                        .image(request.image().getOriginalFilename())
                        .video(request.video().getOriginalFilename())
                        .audio(request.audio().getOriginalFilename())
                        .syllables(request.syllables())
                        .category(request.category())
                        .subcategory(request.subcategory())
                        .userId(item.getUserId())
                        .build();
    }
}
