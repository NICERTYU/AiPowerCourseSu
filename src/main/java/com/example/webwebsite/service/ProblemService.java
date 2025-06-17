package com.example.webwebsite.service;

import com.example.webwebsite.pojo.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author superG
 * @date 2025/6/11
 */


@Service
@Slf4j
public class ProblemService {



    @Value("${zhipu.api.key}")
    private   String API_KEY;
    private static final String API_URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";


    public List<Question> generateQuestions(String subject, String topic, String type) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String prompt = String.format(
                "请为%s中关于“%s”的知识点生成10道%s，每题包括题干、4个选项、正确答案和简要解析。以JSON格式返回，格式如下：\n" +
                        "{ \"questions\": [{ \"question\": \"...\", \"options\": [\"A. ...\", \"B. ...\"], \"answer\": \"A\", \"explanation\": \"...\" }, ...] }",
                subject, topic, type
        );


        Message message = new Message("user", prompt);
        ChatRequest request = new ChatRequest(List.of(message), "glm-4-plus");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode payload = mapper.createObjectNode();
        payload.put("model", request.getModel());
        payload.set("messages", mapper.valueToTree(request.getMessages()));



        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Authorization", "Bearer " + API_KEY);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(payload.toString(), StandardCharsets.UTF_8));

            CloseableHttpResponse response = httpClient.execute(post);
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);


            JsonNode json = mapper.readTree(responseBody);
            log.info("json:{}",json);

            String reply = json.path("choices").get(0).path("message").path("content").asText();
            log.info("reply:{}",reply);

            String jsonl = reply.trim();
            if (jsonl.startsWith("```")) {
                jsonl = jsonl.substring(jsonl.indexOf("\n") + 1);
                jsonl = jsonl.substring(0, jsonl.lastIndexOf("```"));
            }

            jsonl = jsonl.trim();




        ObjectMapper objectMapper = new ObjectMapper();
        QuestionResponse result = objectMapper.readValue(jsonl, QuestionResponse.class);

        return result.getQuestions();


    } catch (IOException e) {
            e.printStackTrace();
           throw e;
        }



    }
}
