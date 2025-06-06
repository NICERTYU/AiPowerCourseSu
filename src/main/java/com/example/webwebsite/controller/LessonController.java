package com.example.webwebsite.controller;

import com.example.webwebsite.pojo.Lesson;
import com.example.webwebsite.pojo.Result;
import com.example.webwebsite.service.LessonService;
import com.example.webwebsite.utils.AliyunOSSOperator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author superG
 * @date 2025/5/30
 */

@Slf4j
@RestController
public class LessonController {


    @Autowired
    private LessonService lessonService;


    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/lesson/upload")
    public Result uploadLesson(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("courseId") Long courseId,
            @RequestParam(value = "duration", required = false) Integer duration,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "sort", required = false) Integer sort,
            HttpServletRequest request
    ) throws Exception {
        String username = (String) request.getAttribute("currentUsername");
        Integer userId = (Integer) request.getAttribute("currentUserId");

        log.info("用户 {} 正在上传文件", username);
        log.info("文件上传，参数：{}",file.getOriginalFilename());

        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());



        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        lesson.setCourseId(courseId);
        lesson.setDuration(duration);
        lesson.setDescription(description);
        lesson.setVideoUrl(url);

        lesson.setSort(sort);

        lessonService.addLesson(lesson);
        return Result.success(url);
    }



    @GetMapping("/lesson/list")
    public Result getLessonsByCourseId(@RequestParam("courseId") Long courseId) {
        log.info("根据课程ID获取课程列表");
        return Result.success(lessonService.getLessonsByCourseId(courseId));
    }



    @GetMapping("/courseallinfo")
    public Result getCourseAllInfo(@RequestParam("courseId") Long courseId) {
        log.info("根据课程ID获取课程列表", courseId);

        return Result.success(lessonService.getAllInfoByCourseId(courseId));
    }





}


