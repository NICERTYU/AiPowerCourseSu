package com.example.webwebsite.pojo;

/**
 * @author superG
 * @date 2025/6/4
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private List<Message> messages;
    private String model;


}

