package com.nicola.testproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    private int id;
    private int questionId;
    private String text;
    private boolean isCorrect;
}
