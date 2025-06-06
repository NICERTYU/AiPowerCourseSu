package com.example.webwebsite.service.impl;

import com.example.webwebsite.mapper.CourseMapper;
import com.example.webwebsite.pojo.Course;
import com.example.webwebsite.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author superG
 * @date 2025/5/30
 */

@Slf4j
@Service
public class CourseServiceImpl  implements CourseService {


    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCourses() {
        return  courseMapper.getCourses();
    }

    @Override
    public List<Course> getUserCourses(Integer userId) {

        return courseMapper.getUserCourses(userId);
    }

    @Override
    public void updateCover(String url, Long courseId) {

        courseMapper.updateCover(courseId, url);
    }

    @Override
    public Course getCourseById(Long id) {

        return courseMapper.getCourseById(id);
    }

    @Override
    public void updateCourse(Course course) {

         log.info("Update course", course);
         course.setUpdatedAt(LocalDateTime.now());
        courseMapper.updateCourse(course);
    }


}
