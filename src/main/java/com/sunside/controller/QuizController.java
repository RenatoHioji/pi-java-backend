package com.sunside.controller;

import com.sunside.model.Quiz;
import com.sunside.service.QuizService;
import com.sunside.utils.IdUtills;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Quiz>> findAll(){
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Quiz> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(quizService.findById(IdUtills.transformToUuid(id)));
    }
}
