package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAnnoMapper {
    @Select("select * from demo_user")
    List<User> findAll();
}
