package com.example.webwebsite.service;

/**
 * @author superG
 * @date 2025/6/5
 */

import com.example.webwebsite.dto.CourseProgressDTO;
import com.example.webwebsite.mapper.CourseViewLogMapper;
import com.example.webwebsite.mapper.LessonMapper;
import com.example.webwebsite.pojo.CourseViewLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    @Autowired
    private CourseViewLogMapper courseViewLogMapper;



    @Autowired
    private LessonMapper lessonMapper;

    // 统计某课程的总观看时长
    public Integer getTotalViewDurationByCourseId(Long courseId) {
        return courseViewLogMapper.getTotalViewDurationByCourseId(courseId);
    }

    // 按分类统计观看次数
    public List<Map<String, Object>> getViewCountByCategory() {
        return courseViewLogMapper.getViewCountByCategory();
    }

    // 统计某学员的观看记录
    public List<CourseViewLog> getUserViewLogs(Integer userId) {
        return courseViewLogMapper.findByUserId(userId);
    }

    // 统计最受欢迎的课程
    public List<Map<String, Object>> getPopularCourses(int limit) {
        return courseViewLogMapper.findTopCoursesByViewCount(limit);
    }


    // 新增：获取用户课程观看进度
    public CourseProgressDTO getCourseProgress(Long courseId, Integer userId) {
        CourseProgressDTO progress = new CourseProgressDTO();
        progress.setCourseId(courseId);
        progress.setUserId(userId);

        // 获取已观看的分集数
        Integer watchedLessons = courseViewLogMapper.getWatchedLessonsCount(courseId, userId);
        progress.setWatchedLessons(watchedLessons != null ? watchedLessons : 0);

        // 获取课程总分集数
        Integer totalLessons = lessonMapper.getTotalLessonsCount(courseId);
        progress.setTotalLessons(totalLessons != null ? totalLessons : 0);

        // 获取用户在该课程的总观看时长
        Integer totalDurationSeconds = courseViewLogMapper.getUserCourseViewDuration(courseId, userId);
        progress.setTotalDurationSeconds(totalDurationSeconds != null ? totalDurationSeconds : 0);

        // 获取课程总时长
        Integer totalCourseDuration = lessonMapper.getTotalCourseDuration(courseId);
        progress.setTotalCourseDuration(totalCourseDuration != null ? totalCourseDuration : 0);

        // 计算完成百分比
        double completionPercentage = (totalCourseDuration == null || totalCourseDuration == 0)
                ? 0.0
                : (double) progress.getTotalDurationSeconds() / totalCourseDuration * 100;
        progress.setCompletionPercentage(Math.round(completionPercentage * 10) / 10.0); // 保留1位小数

        return progress;
    }
}
