package com.sunside.adapters.inbound.controllers;

import com.sunside.domain.Game;
import com.sunside.application.services.GameService;
import com.sunside.utils.IdUtills;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/game")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class GameController {
    private final GameService gameService;

    @GetMapping("{id}")
    public ResponseEntity<Game> listGames(@PathVariable("id") String id){
        return ResponseEntity.ok(gameService.findById(IdUtills.transformToUuid(id)));
    }
}
