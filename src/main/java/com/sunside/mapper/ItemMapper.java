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
                .image(LocalDateTime.now().plusNanos(1) + request.image().getOriginalFilename())
                .video(LocalDateTime.now().plusNanos(2) + request.video().getOriginalFilename())
                .audio(LocalDateTime.now().plusNanos(3) + request.audio().getOriginalFilename())
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
                        .image(LocalDateTime.now().plusNanos(1) + request.image().getOriginalFilename())
                        .video(LocalDateTime.now().plusNanos(2) + request.video().getOriginalFilename())
                        .audio(LocalDateTime.now().plusNanos(3) + request.audio().getOriginalFilename())
                        .syllables(request.syllables())
                        .category(request.category())
                        .subcategory(request.subcategory())
                        .userId(item.getUserId())
                        .build();
    }
}
