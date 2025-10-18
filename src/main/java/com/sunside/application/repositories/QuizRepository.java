package com.sunside.application.repositories;

import com.sunside.domain.Quiz;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository {

    Quiz findRandom();

    Optional<Quiz> findById(UUID id);

    List<Quiz> findAll();
}
