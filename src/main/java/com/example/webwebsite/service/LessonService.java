package com.example.webwebsite.service;

import com.example.webwebsite.pojo.Lesson;

/**
 * @author superG
 * @date 2025/5/30
 */
public interface LessonService {
    void addLesson(Lesson lesson);

    Object getLessonsByCourseId(Long courseId);

    Object getAllInfoByCourseId(Long courseId);
}
