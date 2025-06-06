package com.example.webwebsite.service;

/**
 * @author superG
 * @date 2025/6/5
 */



import com.example.webwebsite.mapper.CourseViewLogMapper;
import com.example.webwebsite.pojo.CourseViewLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseViewLogService {
    @Autowired
    private CourseViewLogMapper courseViewLogMapper;

    public void saveLog(CourseViewLog log) {
        courseViewLogMapper.insert(log);
    }
}
