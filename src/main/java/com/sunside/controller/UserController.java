package com.sunside.controller;

import com.sunside.dto.user.LoginDTORequest;
import com.sunside.dto.user.LoginDTOResponse;
import com.sunside.dto.user.UserDTORequest;
import com.sunside.dto.user.UserDTOResponse;
import com.sunside.security.JwtAuthResponse;
import com.sunside.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<UserDTOResponse> create(@Valid @RequestBody UserDTORequest user){
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
}
