package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Select(
            "select * from user where " +
            "username=#{username} and password=#{password}"
    )
    User findUserByUP(User user);

    @Select("select count(true) from user")
    Long getTotal();

//    @Select(
//            "select * from user " +
//                    "where username like concat('%',#{query},'%') " +
//                    "limit #{size} offset #{beginPage} "
//
//    )
    List<User> findUserByPage(
            @Param("size")      Integer size,
            @Param("beginPage") Integer beginPage,
            @Param("query")     String  query
    );

    @Update("update user set " +
                "status = #{status}, " +
                "updated = #{updated} " +
            "where id = #{id}")
    Integer updateStatus(User user);

    @Insert("insert into user " +
            "(username, password, phone, email, status, created, updated) " +
            "values " +
            "(#{username}, #{password}, #{phone}, #{email}, #{status}, #{created}, #{updated})")
    Integer addUser(User user);

    @Select("select * from user " +
            "where id = #{id}")
    User findUserById(User user);

    @Update("update user set " +
                "username = #{username}, " +
                "phone = #{phone}, " +
                "email = #{email}, " +
                "updated = #{updated} " +
            "where id = #{id}")
    Integer updateUser(User user);

    @Delete("delete from user " +
            "where id = #{id}")
    Integer deleteUser(Integer id);
}
