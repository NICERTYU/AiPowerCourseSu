package com.example.webwebsite.service.impl;

import com.example.webwebsite.mapper.CourseMapper;
import com.example.webwebsite.mapper.LessonMapper;
import com.example.webwebsite.pojo.Course;
import com.example.webwebsite.pojo.CourseAllLesson;
import com.example.webwebsite.pojo.Lesson;
import com.example.webwebsite.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author superG
 * @date 2025/5/30
 */

@Service
@Slf4j
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;


    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void addLesson(Lesson lesson) {
        lesson.setCreatedAt(LocalDateTime.now());
        lesson.setUpdatedAt(LocalDateTime.now());

        lessonMapper.addLesson(lesson);

    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {

        return lessonMapper.getLessonsByCourseId(courseId);
    }

    @Override
    public CourseAllLesson getAllInfoByCourseId(Long courseId) {

        Course course = courseMapper.getCourseById(courseId);
        List<Lesson> lessons = lessonMapper.getLessonsByCourseId(courseId);



        return new CourseAllLesson(course.getId(), course.getTitle(), course.getDescription(),  course.getCoverUrl(), lessons);





    }
}
