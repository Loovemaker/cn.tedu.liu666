package cn.tedu.mybatis.demo2;

import cn.tedu.mybatis.demo2.mapper.UserMapper2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    String resourcePath = "mybatis/mybatis-config.xml";

    SqlSession getSession(String resourcePath) {
        SqlSession result;

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

    // TODO: Find out WHY ALIAS functionalitiy in the fuxxin xml cunt FULLY work wotule
    @Test void findUser() {
        try (final SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper2.class)
                    .listAllUsers()
                    .forEach(System.out::println);
        }
    }
}
