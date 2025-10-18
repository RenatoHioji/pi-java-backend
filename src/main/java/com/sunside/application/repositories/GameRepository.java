package com.sunside.application.repositories;

import com.sunside.domain.Game;

import java.util.List;
import java.util.UUID;

public interface GameRepository {

    Game findById(UUID id);
    
}
