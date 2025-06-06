package com.example.webwebsite.pojo;

/**
 * @author superG
 * @date 2025/5/30
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long id;                // 课程ID
    private String title;           // 课程标题
    private String coverUrl;        // 课程封面图片
    private String description;     // 课程简介
    private Long teacherId;         // 讲师ID
    private Long categoryId;        // 分类ID
    private Double price;           // 价格
    private Integer learners;       // 学习人数
    private Integer status;         // 状态（0下架 1上架）
    private LocalDateTime createdAt;// 创建时间
    private LocalDateTime updatedAt;// 更新时间
}