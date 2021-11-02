package cn.tedu.jt.ssm.mapper;

import cn.tedu.jt.ssm.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Set;


public interface UserMapper {
    Set<User> findAll();

    @Select("select * from demo_user where id = #{id}")
    User findUserById(Integer id);

    Set<User> findUserByIds(Integer[] ids);

    @Select("select * from demo_user where age = #{age} and sex = #{sex}")
    Set<User> findUserByAS(User user);

    @Update("update demo_user " +
            "set name = #{name}, age = #{age}, sex = #{sex} " +
            "where id = #{id}")
    Integer updateUserById(User user);

    @Select("select * from demo_user where name like \"%\"#{name}\"%\"")
    Set<User> findUserContainName(String name);

}
