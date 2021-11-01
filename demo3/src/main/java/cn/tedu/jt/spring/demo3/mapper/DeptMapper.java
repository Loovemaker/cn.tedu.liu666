package cn.tedu.jt.spring.demo3.mapper;

import cn.tedu.jt.spring.demo3.pojo.Dept;

import java.util.Set;

public interface DeptMapper {
    Set<Dept> findAll();

    Set<Dept> selectChildren();
}
