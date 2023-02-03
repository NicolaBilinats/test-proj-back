package com.nicola.testproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    private int id;
    private UUID userId;
    private Integer questionId;
    private Integer optionId;
    private String answer;


    public Answer(UUID userId, Integer questionId, Integer optionId, String answer) {
        this.userId = userId;
        this.questionId = questionId;
        this.optionId = optionId;
        this.answer = answer;
    }
}

