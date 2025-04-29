package com.sunside.repository;

import com.sunside.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    @Query(value = "SELECT * FROM quizzes ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Quiz findRandom();
}
