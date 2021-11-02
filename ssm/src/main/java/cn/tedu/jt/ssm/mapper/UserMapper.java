package cn.tedu.jt.ssm.mapper;

import cn.tedu.jt.ssm.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;


@Mapper
public interface UserMapper {
    Set<User> findAll();

}
