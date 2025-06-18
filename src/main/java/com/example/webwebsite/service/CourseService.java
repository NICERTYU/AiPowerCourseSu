package com.example.webwebsite.service;

import com.example.webwebsite.pojo.Course;

import java.util.List;

/**
 * @author superG
 * @date 2025/5/30
 */
public interface CourseService {
    List<Course> getCourses();

    List<Course> getUserCourses(Integer userId);


    void updateCover(String url, Long courseId);

    Course getCourseById(Long id);

    void updateCourse(Course course);

    void deleteCourse(Long id);

    void createCourse(Course course);
}
