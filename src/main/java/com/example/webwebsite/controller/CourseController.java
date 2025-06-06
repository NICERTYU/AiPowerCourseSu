package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.Course;
import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.service.CourseService;
import com.example.webwebsite.utils.AliyunOSSOperator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author superG
 * @date 2025/5/30
 */


@RestController
@Slf4j
@RequestMapping("/api")
public class CourseController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;



    @GetMapping("/courses")
    public Result getCourses()
    {
        List<Course>   courses = courseService.getCourses();
        return Result.success(courses);
    }


    @GetMapping("/usercourse")
    public Result getUserCourses(@RequestParam Integer userId)
    {
        log.info("getUserCourses: userId={}", userId);
        List<Course>   courses = courseService.getUserCourses(userId);
        return Result.success(courses);
    }

    @PostMapping("/uploadcover")
    public Result upload(MultipartFile file, HttpServletRequest request, @RequestParam Long courseId) throws Exception {
        String username = (String) request.getAttribute("currentUsername");
        Integer userId = (Integer) request.getAttribute("currentUserId");

        log.info("用户 {} 正在上传文件", username);
        log.info("文件上传，参数：{}",file.getOriginalFilename());

        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());

        log.info("文件上传成功，返回结果：{}",url);
        courseService.updateCover(url, courseId);

        return Result.success(url);
    }

    @GetMapping ("/courses/{id}")
    public Result getCourse(@PathVariable Long id)
    {
        Course course = courseService.getCourseById(id);
        return Result.success(course);
    }


    @PostMapping("/courses")
    public Result addCourse(@RequestBody Course course)
    {
        courseService.updateCourse(course);
        return Result.success();
    }









}
