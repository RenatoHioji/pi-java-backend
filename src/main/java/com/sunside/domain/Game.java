package com.sunside.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Game {
    private UUID id;
    private Integer correctAnswer;
    private Integer type;
    private UUID quizId;
    private Set<Item> items = new HashSet<>();

    public Game() {
    }

    public Game(UUID id, Integer correctAnswer, Integer type, UUID quizId, Set<Item> items) {
        this.id = id;
        this.correctAnswer = correctAnswer;
        this.type = type;
        this.quizId = quizId;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public UUID getQuizId() {
        return quizId;
    }

    public void setQuizId(UUID quizId) {
        this.quizId = quizId;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
