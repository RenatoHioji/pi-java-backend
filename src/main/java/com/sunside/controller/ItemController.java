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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
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

    @GetMapping("/user/{userId}")
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

    @GetMapping("{id}")
    ResponseEntity<Item> findById(@PathVariable("id") String id, Principal principal){
        return ResponseEntity.ok(itemService.findById(IdUtills.transformToUuid(id), principal.getName()));
    }

    @GetMapping("recents")
    ResponseEntity<List<Item>> findRecents(Principal principal){
        return ResponseEntity.ok(itemService.findRecents(principal.getName()));
    }
    @GetMapping("history")
    ResponseEntity<List<Item>> findHistory(Principal principal){
        return ResponseEntity.ok(itemService.findMoreViewed(principal.getName()));
    }

    @GetMapping(path ="/foto", consumes = {"multipart/form-data"})
    ResponseEntity<String> searchCategory(
            @NotNull(message = "Imagem não pode ser nula.")
            @NotBlank(message = "Image não poed estar vazia.")
            MultipartFile image
    ){
        return ResponseEntity.ok(itemService.searchCategory(image));
    }
}
