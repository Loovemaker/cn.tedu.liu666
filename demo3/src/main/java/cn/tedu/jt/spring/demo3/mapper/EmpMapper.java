package cn.tedu.jt.spring.demo3.mapper;

import cn.tedu.jt.spring.demo3.pojo.Emp;

import java.util.Set;

public interface EmpMapper {
    Set<Emp> findAll();
}
