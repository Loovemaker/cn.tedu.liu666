package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
