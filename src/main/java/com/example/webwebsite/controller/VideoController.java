package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.service.VideoSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author superG
 * @date 2025/6/3
 */
@RestController
@RequestMapping("/api/video")
@Slf4j
public class VideoController {

    @Autowired
    private VideoSummaryService videoSummaryService;

    @GetMapping("/summary")
    public Result getSummary(@RequestParam String url) throws IOException, InterruptedException {

            String summary = videoSummaryService.summarizeVideoByUrl(url);
            log.info("视频摘要：{}", summary);
            return Result.success(summary);

    }
}
