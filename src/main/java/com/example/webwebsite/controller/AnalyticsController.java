package com.example.webwebsite.controller;

/**
 * @author superG
 * @date 2025/6/5
 */


import com.example.webwebsite.dto.CourseProgressDTO;
import com.example.webwebsite.dto.UserCourseViewDTO;
import com.example.webwebsite.pojo.CourseViewLog;
import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/popular-courses")
    public Result getPopularCourses(@RequestParam(defaultValue = "10") int limit) {
        return Result.success(analyticsService.getPopularCourses(limit));
    }

    @GetMapping("/user-duration/{userId}")
    public Result getUserTotalViewDuration(@PathVariable Integer userId) {
        List<CourseViewLog> logs = analyticsService.getUserViewLogs(userId);
        return Result.success(logs.stream().mapToInt(CourseViewLog::getDurationSeconds).sum());
    }

    @GetMapping("/category-view-count")
    public Result getViewCountByCategory() {
        return Result.success(analyticsService.getViewCountByCategory());
    }


    @GetMapping("/progress/{courseId}")
    public Result getCourseProgress(
            @PathVariable Long courseId,
            @RequestParam Integer userId) {
        CourseProgressDTO progress = analyticsService.getCourseProgress(courseId, userId);
        return Result.success(progress);
    }


    @GetMapping("/user-viewdata/{userId}")
    public Result getUserTotalViewData(@PathVariable Integer userId) {
        List<CourseViewLog> logs = analyticsService.getUserViewLogs(userId);
        return Result.success(logs);
    }



    @GetMapping("/user/{userId}")
    public Result getUserCourseViewData(@PathVariable Integer userId) {
        List<UserCourseViewDTO> result = analyticsService.getUserCourseViewData(userId);
        return Result.success(result);
    }
}