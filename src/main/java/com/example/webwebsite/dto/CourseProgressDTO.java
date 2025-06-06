package com.example.webwebsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author superG
 * @date 2025/6/5
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseProgressDTO {
    private Long courseId;
    private Integer userId;
    private Integer watchedLessons; // 已观看的分集数
    private Integer totalLessons; // 课程总分集数
    private Integer totalDurationSeconds; // 总观看时长（秒）
    private Integer totalCourseDuration; // 课程总时长（秒）
    private Double completionPercentage; // 完成百分比

}
