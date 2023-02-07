package com.nicola.testproj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicola.testproj.model.Answer;
import com.nicola.testproj.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static java.lang.Math.random;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID userId;

    @BeforeEach
    public void setUp() throws Exception {
        User user = new User(UUID.randomUUID(), "Nikola " + random());
        String userJson = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                )
                .andExpect(status().isCreated())
                .andReturn();

        this.userId = objectMapper.readValue(result.getResponse().getContentAsString(), User.class).getId();
    }
//
//    @Test
//    public void testCreateAnswerForOptionType() throws Exception {
//        Answer answer = new Answer(this.userId, 0, 1, null);
//        String answerJson = objectMapper.writeValueAsString(answer);
//
//        mockMvc.perform(post("/api/answers")
//                        .header("userId", userId.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(answerJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testCreateAnswerForChoiceType() throws Exception {
//        Answer answer = new Answer(this.userId, 1, null, "answer");
//        String answerJson = objectMapper.writeValueAsString(answer);
//
//        mockMvc.perform(post("/api/answers")
//                        .header("userId", userId.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(answerJson))
//                .andExpect(status().isOk());
//    }

}

