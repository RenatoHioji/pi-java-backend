package com.sunside.application.services;

import com.sunside.infrastructure.exceptions.BusinessException;
import com.sunside.domain.Game;
import com.sunside.adapters.outbound.repositories.JpaGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final JpaGameRepository gameRepository;

    public Game findById(UUID id){
        return gameRepository.findById(id).orElseThrow(() -> new BusinessException("Game não encontrado"));
    }
}
