package com.example.webwebsite.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author superG
 * @date 2025/5/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAllLesson {
     private Long id;
     private String title;
     private String description;
     private String videoUrl;


     List<Lesson> lessons;



}
