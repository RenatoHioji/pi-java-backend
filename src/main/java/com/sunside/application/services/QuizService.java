
package com.sunside.application.services;

import com.sunside.infrastructure.exceptions.BusinessException;
import com.sunside.domain.Quiz;
import com.sunside.adapters.outbound.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    public List<Quiz> findAll(){
        return quizRepository.findAll();
    }

    public Quiz findById(UUID id){
        return quizRepository.findById(id).orElseThrow(() -> new BusinessException("Quiz não foi encontrado"));
    }
    public Quiz findRandom(){
        return quizRepository.findRandom();
    }

}
