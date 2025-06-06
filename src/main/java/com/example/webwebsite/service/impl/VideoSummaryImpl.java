package com.example.webwebsite.service.impl;

import com.example.webwebsite.service.VideoSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

/**
 * @author superG
 * @date 2025/6/3
 */
@Slf4j
@Service
public class VideoSummaryImpl implements VideoSummaryService {
    // 替换为豆包开放平台的API Key
    @Value("${zhipu.api.key}")
    private String glmApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String summarizeVideoByUrl(String videoUrl) throws IOException, InterruptedException {
        // 1. 准备调用本地 Whisper 服务
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = "http://localhost:5005/transcribe";

        // 2. 构造请求体（multipart/form-data）
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("url", videoUrl);  // 你的 Flask 服务要求字段名为 url

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 3. 调用 Whisper 服务，获取转录文本
        ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Object textObj = response.getBody().get("text");
            String transcript = textObj != null ? textObj.toString() : "";

            // 4. 使用 GLM 进行总结
            return summarizeWithGlm(transcript);
        } else {
            throw new RuntimeException("调用 Whisper Flask 服务失败，状态：" + response.getStatusCode());
        }
    }







    public String summarizeWithGlm(String transcript) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + glmApiKey); // 智谱认证方式
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "GLM-4-airx");  // 或 "glm-3-turbo"，根据你购买的模型决定

        payload.put("messages", List.of(
                Map.of("role", "system", "content", "你是一个专业的视频内容总结助手，需要将用户提供的视频文字稿提炼为简洁的摘要。"),
                Map.of("role", "user", "content", "请总结以下视频内容：" + transcript)
        ));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://open.bigmodel.cn/api/paas/v4/chat/completions",  // 智谱 GLM 接口
                request,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                return (String) message.get("content");
            }
        }
        throw new RuntimeException("GLM 总结接口调用失败：" + response);
    }

}