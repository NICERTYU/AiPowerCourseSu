package com.example.webwebsite.service;

import java.io.IOException;

/**
 * @author superG
 * @date 2025/6/3
 */
public interface VideoSummaryService {
    String summarizeVideoByUrl(String url) throws IOException, InterruptedException;
}
