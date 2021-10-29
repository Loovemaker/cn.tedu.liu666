package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.Dog;

import java.util.List;

public interface DogMapper {
    List<Dog> findAll();
}
