package com.example.webwebsite.mapper;

import com.example.webwebsite.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author superG
 * @date 2025/5/30
 */



@Mapper
public interface CourseMapper {




    @Select("select * from course")
    List<Course> getCourses();



    @Select("select * from course where teacher_id = #{userId}")
    List<Course> getUserCourses(Integer userId);



    @Update("update course set cover_url = #{url} where id = #{courseId}")
    void updateCover(Long courseId, String url);


    @Select("select * from course where id = #{id}")
    Course getCourseById(Long id);


    void updateCourse(Course course);

    @Update("delete from course where id = #{id}")
    void deleteCourse(Long id);


    @Update("insert into course (title, description, teacher_id) values (#{title}, #{description}, #{teacherId})")
    void createCourse(Course course);
}
