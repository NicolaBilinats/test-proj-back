package com.nicola.testproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private Integer questionId;
    private Integer optionId;
    private String answer;

}

