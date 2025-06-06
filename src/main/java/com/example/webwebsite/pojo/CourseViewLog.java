package com.example.webwebsite.pojo;

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
public class CourseViewLog {
    private Long id;
    private Integer userId; // 对应 user 表的 INT UNSIGNED
    private Long courseId;
    private Long lessonId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer durationSeconds;
    private String device;
    private String region;
    private LocalDateTime createdAt;

}
