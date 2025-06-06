package com.example.webwebsite.dto;

/**
 * @author superG
 * @date 2025/6/5
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseViewLogDTO {
    private Integer userId;
    private Long courseId;
    private Long lessonId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer durationSeconds;
    private String device;
    private String region;


}