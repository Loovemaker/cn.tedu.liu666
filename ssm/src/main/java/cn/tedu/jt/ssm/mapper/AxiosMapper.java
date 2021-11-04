package cn.tedu.jt.ssm.mapper;

import cn.tedu.jt.ssm.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AxiosMapper {

    @Insert("insert into demo_user (name, age, sex) value (#{name}, #{age}, #{sex})")
    Integer saveUser(User user);

    @Select("select * from demo_user order by id desc")
    List<User> findUserList();
}
