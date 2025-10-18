package com.sunside.adapters.inbound.controllers;

import com.sunside.adapters.inbound.dto.LoginDTORequest;
import com.sunside.adapters.outbound.dto.LoginDTOResponse;
import com.sunside.adapters.inbound.dto.UserDTORequest;
import com.sunside.adapters.outbound.dto.UserDTOResponse;
import com.sunside.application.services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<LoginDTOResponse> create(@Valid @RequestBody UserDTORequest user){
        return ResponseEntity.status(201).body(userService.create(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") @Parameter(example = "1e283e58-6273-4242-831e-3eb845a692ff") String id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public ResponseEntity<LoginDTOResponse> login(@Valid @RequestBody LoginDTORequest loginDTORequest){
        return ResponseEntity.ok(userService.login(loginDTORequest));
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDTOResponse> findByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("{id}")
    public ResponseEntity<LoginDTOResponse> update(@PathVariable("id") @Parameter(example = "1e283e58-6273-4242-831e-3eb845a692ff") String id, @Valid @RequestBody LoginDTORequest loginDTORequest){
        return ResponseEntity.ok(userService.updateById(id, loginDTORequest));
    }
}
