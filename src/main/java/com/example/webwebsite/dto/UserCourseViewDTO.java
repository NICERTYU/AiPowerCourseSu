package com.example.webwebsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author superG
 * @date 2025/6/6
 */




@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseViewDTO {
    private Long courseId;
    private String courseTitle;
    private Integer totalDurationSeconds;


}

