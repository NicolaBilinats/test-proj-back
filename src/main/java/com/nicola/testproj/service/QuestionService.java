package com.nicola.testproj.service;

import com.nicola.testproj.dao.QuestionRepository;
import com.nicola.testproj.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestion(int questionId) {
        return questionRepository.getQuestionWithOptions(questionId);
    }
}

