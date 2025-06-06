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
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private Long id;                // 分集ID
    private Long courseId;          // 所属课程ID
    private String title;           // 分集标题
    private String videoUrl;        // 视频播放地址
    private Integer duration;       // 视频时长（秒）
    private Integer sort;           // 排序号
    private String description;     // 分集简介
    private LocalDateTime createdAt;// 创建时间
    private LocalDateTime updatedAt;// 更新时间
    private String audioUrl;
}
