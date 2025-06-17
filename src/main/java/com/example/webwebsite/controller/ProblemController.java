package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.Question;
import com.example.webwebsite.pojo.QuestionRequest;
import com.example.webwebsite.pojo.QuestionResponse;
import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author superG
 * @date 2025/6/11
 */

@RestController
@Slf4j
public class ProblemController {

    @Autowired
    private ProblemService chatGLMService;

    @PostMapping("/generate-questions")
    public Result generate(@RequestBody QuestionRequest request) throws IOException {
        List<Question> questions = chatGLMService.generateQuestions(
                request.getSubject(), request.getTopic(), request.getType()
        );
        QuestionResponse response = new QuestionResponse();
        response.setQuestions(questions);
        return Result.success(response);
    }
}
