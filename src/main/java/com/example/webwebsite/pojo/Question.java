package com.example.webwebsite.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author superG
 * @date 2025/6/11
 */
@Data
public class Question {
    private String question;
    private List<String> options;
    private String answer;
    private String explanation;
}

