package com.example.webwebsite.mapper;

/**
 * @author superG
 * @date 2025/6/5
 */

import com.example.webwebsite.pojo.CourseViewLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseViewLogMapper {
    void insert(CourseViewLog log);

    List<CourseViewLog> findByUserId(Integer userId);

    List<CourseViewLog> findByCourseId(Long courseId);

    Integer getTotalViewDurationByCourseId(Long courseId);

    List<Map<String, Object>> getViewCountByCategory();

    List<Map<String, Object>> findTopCoursesByViewCount(int limit);


    // 新增：获取用户在指定课程的已观看分集数
    Integer getWatchedLessonsCount(Long courseId, Integer userId);

    // 新增：获取用户在指定课程的总观看时长
    Integer getUserCourseViewDuration(Long courseId, Integer userId);
}
