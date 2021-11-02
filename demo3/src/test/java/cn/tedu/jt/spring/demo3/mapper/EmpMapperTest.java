package cn.tedu.jt.spring.demo3.mapper;

import cn.tedu.jt.spring.demo3.pojo.Emp;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class EmpMapperTest {

    String resourcePath = "mybatis/mybatis-config.xml";

    SqlSession getSession(String resourcePath) {
        try (InputStream resourceAsStream = org.apache.ibatis.io.Resources.getResourceAsStream(resourcePath)) {

            // build SQL session factory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            return sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Test
    void findAll() {
        try (final SqlSession session = getSession(resourcePath)) {
            session
                    .getMapper(EmpMapper.class)
                    .findAll()
                    .forEach(System.out::println);
        }
    }

    @Test void findAllSelect() {
        try (final SqlSession session = getSession(resourcePath)) {
            session
                    .getMapper(EmpMapper.class)
                    .findAllSelect()
                    .forEach(System.out::println);
        }
    }
}