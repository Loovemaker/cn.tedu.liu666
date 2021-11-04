package cn.tedu.jt.ssm.mapper;

import cn.tedu.jt.ssm.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AxiosMapper {

    @Insert("insert into demo_user (name, age, sex) value (#{name}, #{age}, #{sex})")
    Integer saveUser(User user);

    @Select("select * from demo_user order by id desc")
    List<User> findUserList();

    @Update("update demo_user " +
            "set name = #{name}, age = #{age}, sex = #{sex} " +
            "where id = #{id}")
    Integer updateUser(User user);

    @Delete("delete from demo_user where id = #{id}")
    Integer deleteUser(Integer id);
}
