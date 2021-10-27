package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();
}
