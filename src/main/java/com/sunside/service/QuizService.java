
package com.sunside.service;

import com.sunside.exceptions.BusinessException;
import com.sunside.model.Quiz;
import com.sunside.repository.QuizRepository;
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

}
