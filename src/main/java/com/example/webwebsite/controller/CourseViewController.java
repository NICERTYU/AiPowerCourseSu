package com.example.webwebsite.controller;

/**
 * @author superG
 * @date 2025/6/5
 */



import com.example.webwebsite.dto.CourseViewLogDTO;
import com.example.webwebsite.pojo.CourseViewLog;
import com.example.webwebsite.service.CourseViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/view")
public class CourseViewController {
    @Autowired
    private CourseViewLogService courseViewLogService;

    @PostMapping("/log")
    public ResponseEntity<String> logCourseView(@RequestBody CourseViewLogDTO logDTO) {
        CourseViewLog log = new CourseViewLog();
        log.setUserId(logDTO.getUserId());
        log.setCourseId(logDTO.getCourseId());
        log.setLessonId(logDTO.getLessonId());
        log.setStartTime(logDTO.getStartTime());
        log.setEndTime(logDTO.getEndTime());
        log.setDurationSeconds(logDTO.getDurationSeconds());
        log.setDevice(logDTO.getDevice());
        log.setRegion(logDTO.getRegion());

        courseViewLogService.saveLog(log);
        return ResponseEntity.ok("View log saved successfully");
    }
}
