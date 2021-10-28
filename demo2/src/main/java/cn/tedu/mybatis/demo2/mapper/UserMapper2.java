package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.User2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper2 {
    List<User2> listAllUsers();
}
