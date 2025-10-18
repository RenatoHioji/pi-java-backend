package com.sunside.adapters.outbound.repositories;

import com.sunside.adapters.outbound.entities.JpaQuizEntity;
import com.sunside.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaQuizRepository extends JpaRepository<JpaQuizEntity, UUID> {
    @Query(value = "SELECT * FROM quizzes ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    JpaQuizEntity findRandom();
}
