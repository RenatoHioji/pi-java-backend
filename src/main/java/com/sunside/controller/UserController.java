package com.sunside.controller;

import com.sunside.dto.LoginDTORequest;
import com.sunside.dto.UserDTORequest;
import com.sunside.dto.UserDTOResponse;
import com.sunside.security.JwtAuthResponse;
import com.sunside.serice.UserService;
import com.sunside.model.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> create(@Valid UserDTORequest user){
        return ResponseEntity.status(201).body(userService.create(user));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathParam("id") @Parameter(example = "1e283e58-6273-4242-831e-3eb845a692ff") String id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> login(@Valid  LoginDTORequest loginDTORequest){
        return ResponseEntity.ok(new JwtAuthResponse(userService.login(loginDTORequest)));
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDTOResponse> findByUsername(@PathParam("username") String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }
}
