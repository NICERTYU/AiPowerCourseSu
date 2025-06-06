package com.example.webwebsite.mapper;

import com.example.webwebsite.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author superG
 * @date 2025/5/29
 */

@Mapper
public interface UserMapper {



    @Insert("insert into user(username,password,nickname,avatar,email,phone,role,status,created_at,updated_at) values(#{username},#{password},#{nickname},#{avatar},#{email},#{phone},#{role},#{status},#{createdAt},#{updatedAt})")
    void registerUser(User user);



    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);



    @Select("select * from user where username = #{username}")
    User getUserByUsernameAndPassword(String username);



    @Update("update user set avatar = #{url} where id = #{userId}")
    void updateAvatar(Integer userId, String url);
}
