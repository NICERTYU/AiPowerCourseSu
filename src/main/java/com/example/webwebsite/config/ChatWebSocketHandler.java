package com.example.webwebsite.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author superG
 * @date 2025/6/4
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {


    @Value("${zhipu.api.key}")
    private  static String API_KEY;
    private static final String API_URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(message.getPayload());

        ObjectNode payload = mapper.createObjectNode();
        payload.put("model", root.get("model").asText());
        payload.put("stream", true);
        payload.set("messages", root.get("messages"));

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofLines())
                .thenAccept(response -> {
                    response.body().forEach(line -> {
                        try {
                            if (line.startsWith("data:")) {
                                String content = line.substring(5).trim();
                                if (!content.equals("[DONE]")) {
                                    String replyChunk = mapper.readTree(content)
                                            .path("choices").get(0).path("delta").path("content").asText();
                                    session.sendMessage(new TextMessage(replyChunk));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                });
    }
}

