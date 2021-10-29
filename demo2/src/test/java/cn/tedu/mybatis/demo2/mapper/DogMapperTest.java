package cn.tedu.mybatis.demo2.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class DogMapperTest {
    String resourcePath = "mybatis/mybatis-config.xml";

    SqlSession getSession(String resourcePath) {
        try (InputStream resourceAsStream =
                     org.apache.ibatis.io.Resources.getResourceAsStream(resourcePath)) {

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
            session.getMapper(DogMapper.class)
                    .findAll()
                    .forEach(System.out::println);
        }
    }
}