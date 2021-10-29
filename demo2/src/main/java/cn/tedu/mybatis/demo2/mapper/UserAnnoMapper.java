package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAnnoMapper {
    @Select("select * from demo_user")
    List<User> findAll();

    @Insert("insert into demo_user" +
            "(id, name, age, sex) " +
            "value (#{id}, #{name}, #{age}, #{sex})")
    Integer saveUser(User user);
}
