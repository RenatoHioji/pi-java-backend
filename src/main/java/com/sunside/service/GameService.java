package com.sunside.service;

import com.sunside.exceptions.BusinessException;
import com.sunside.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Game findById(UUID id){
        return gameRepository.findById(id).orElseThrow(() -> new BusinessException("Game não encontrado"));
    }
}
