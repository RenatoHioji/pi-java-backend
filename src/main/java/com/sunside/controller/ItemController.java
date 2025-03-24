package com.sunside.controller;

import com.sunside.dto.item.ItemDTORequest;
import com.sunside.model.Item;
import com.sunside.service.ItemService;
import com.sunside.utils.IdUtills;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/item")
@SecurityRequirement(name = "Authorization")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    ResponseEntity<List<Item>> findAll(){
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("{userId}")
    ResponseEntity<List<Item>> findAllByUserId(@Parameter(example = "") @PathVariable("userId") String userId){
        return ResponseEntity.ok(itemService.findAllByUserId(IdUtills.transformToUuid(userId)));
    }

    @PostMapping(path = "/user/{userId}", consumes = {"multipart/form-data"})
    ResponseEntity<Item> createItemToUser(@PathVariable("userId") String userId, @Valid @ModelAttribute ItemDTORequest request){
        return ResponseEntity.status(201).body(itemService.createToUser(request, IdUtills.transformToUuid(userId)));

    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteItem(@PathVariable("id") String id){
        itemService.delete(IdUtills.transformToUuid(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}", consumes = {"multipart/form-data"})
    ResponseEntity<Item> updateItem(@PathVariable("id") String id, @Valid @ModelAttribute ItemDTORequest request){
        return ResponseEntity.ok(itemService.update(IdUtills.transformToUuid(id), request));
    }
}
