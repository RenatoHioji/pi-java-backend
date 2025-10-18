package com.sunside.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Quiz {
    private UUID id;

    private Integer nivel;

    private Set<Game> games = new HashSet<>();

    public Quiz(UUID id, Integer nivel, Set<Game> games) {
        this.id = id;
        this.nivel = nivel;
        this.games = games;
    }

    public Quiz() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
