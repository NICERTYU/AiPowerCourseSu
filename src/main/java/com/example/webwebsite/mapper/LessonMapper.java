package com.example.webwebsite.mapper;

import com.example.webwebsite.pojo.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author superG
 * @date 2025/5/30
 */

@Mapper
public interface LessonMapper {




    @Select("insert into lesson(title,duration,description,course_id, video_url, sort) values(#{title},#{duration},#{description},#{courseId}, #{videoUrl}, #{sort})")
    void addLesson(Lesson lesson);



    @Select("select * from lesson where course_id = #{courseId}")
    List<Lesson> getLessonsByCourseId(Long courseId);



    // 获取课程的总分集数
    Integer getTotalLessonsCount(Long courseId);

    // 获取课程的总时长（秒）
    Integer getTotalCourseDuration(Long courseId);


}
