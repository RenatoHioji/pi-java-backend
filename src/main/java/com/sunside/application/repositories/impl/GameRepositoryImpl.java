package com.sunside.application.repositories.impl;

import com.sunside.adapters.outbound.entities.JpaItemEntity;
import com.sunside.adapters.outbound.entities.JpaUserEntity;
import com.sunside.adapters.outbound.repositories.JpaGameRepository;
import com.sunside.application.repositories.GameRepository;
import com.sunside.domain.Game;
import com.sunside.domain.Item;
import com.sunside.domain.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameRepositoryImpl implements GameRepository {
    private final JpaGameRepository jpaGameRepository;

    public GameRepositoryImpl(JpaGameRepository jpaGameRepository){
        this.jpaGameRepository = jpaGameRepository;
    }

    @Override
    public Game findById(UUID id) {
        return jpaGameRepository.findById(id)
                .map(entity -> new Game(entity.getId(), entity.getCorrectAnswer(), entity.getType(), entity.getQuizId(), this.mapToItem(entity.getItems())))
                .orElse(null);
    }

    private Set<Item> mapToItem(Set<JpaItemEntity> jpaItemEntity){
        return jpaItemEntity
                .stream()
                .map(entity -> new Item(entity.getId(), entity.getName(), entity.getSyllables(), entity.getImage(), entity.getVideo(),
                        entity.getAudio(), entity.getCategory(), entity.getSubcategory(), entity.getUserId(), mapToUser(entity.getUsersHistory())))
                .collect(Collectors.toSet());
    }

    private Set<User> mapToUser(Set<JpaUserEntity> jpaUserEntities){
        return jpaUserEntities
                .stream()
                .map(entity -> new User(entity.getId(), entity.getUsername()))
                .collect(Collectors.toSet());
    }
}
