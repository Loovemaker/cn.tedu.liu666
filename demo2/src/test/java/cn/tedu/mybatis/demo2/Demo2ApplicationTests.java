package cn.tedu.mybatis.demo2;

import cn.tedu.mybatis.demo2.mapper.UserMapper;
import cn.tedu.mybatis.demo2.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

//@SpringBootTest
class Demo2ApplicationTests {

    @Test void contextLoads() { }

    @Test void MybatisTest1() throws IOException {
        Object users = null;

        // set resource path
        String resourcePath = "mybatis/mybatis-config.xml";

        try (InputStream resourceAsStream =
                     org.apache.ibatis.io.Resources.getResourceAsStream(resourcePath);) {

            // build SQL session factory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

            // get session
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                // get mapper
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

                // use mapper
                users = userMapper.findAll();
                System.out.println(users);
            }
        }

        System.out.println(users);
    }

}
