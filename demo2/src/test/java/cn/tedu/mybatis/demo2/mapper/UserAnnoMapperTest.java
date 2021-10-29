package cn.tedu.mybatis.demo2.mapper;

import cn.tedu.mybatis.demo2.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UserAnnoMapperTest {

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

    Random random = new Random();

    String randomGender() {
        return random.nextBoolean() ? "男" : "女";
    }

    @Test
    void findAll() {
        try (final SqlSession session = getSession(resourcePath)) {
            session.getMapper(UserAnnoMapper.class)
                    .findAll()
                    .forEach(System.out::println);
        }
    }

    @Test
    void saveUser() {
        try (final SqlSession session = getSession(resourcePath)) {
            System.out.printf("%d rows affected\n", session.getMapper(UserAnnoMapper.class)
                    .saveUser(
                            new User(null, "注解测试", 18, randomGender())
                    ));
        }
    }
}