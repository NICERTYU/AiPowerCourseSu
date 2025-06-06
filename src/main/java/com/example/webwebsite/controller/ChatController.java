package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.ChatRequest;
import com.example.webwebsite.pojo.ChatResponse;
import com.example.webwebsite.pojo.Message;
import com.example.webwebsite.pojo.Result;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author superG
 * @date 2025/6/4
 */


@RestController
@Slf4j
public class ChatController {



    @Value("${zhipu.api.key}")
    private   String API_KEY;
    private static final String API_URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";




    @PostMapping("/chat")
    public Result chat(@RequestBody ChatRequest chatRequest) throws IOException {
        // 构建请求体
        log.info("chatRequest:{}",chatRequest);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode payload = mapper.createObjectNode();
        payload.put("model", chatRequest.getModel());
        payload.set("messages", mapper.valueToTree(chatRequest.getMessages()));

        // 发送请求
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Authorization", "Bearer " + API_KEY);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(payload.toString(), StandardCharsets.UTF_8));

            CloseableHttpResponse response = httpClient.execute(post);
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);


            JsonNode json = mapper.readTree(responseBody);

            String reply = json.path("choices").get(0).path("message").path("content").asText();
            log.info("reply:{}",reply);
            return Result.success(new ChatResponse(reply));
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(new ChatResponse("调用出错：" + e.getMessage()));
        }
    }
}
