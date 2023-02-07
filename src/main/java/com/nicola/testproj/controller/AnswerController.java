package com.nicola.testproj.controller;

import com.nicola.testproj.model.Answer;
import com.nicola.testproj.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<Void> createAnswer(@RequestHeader("userId") String userId, @RequestBody Answer answer) {
        answerService.addAnswer(userId, answer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public List<Answer> getAnswersByUserId(@RequestHeader("userId") String userId) {
        return answerService.getAnswersByUserId(userId);
    }
}

