package cn.tedu.mybatis.demo2;

import cn.tedu.mybatis.demo2.mapper.UserMapper;
import cn.tedu.mybatis.demo2.pojo.User;
import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;
import java.util.stream.IntStream;

//@SpringBootTest
class Demo2ApplicationTests {

    String resourcePath = "mybatis/mybatis-config.xml";

    Random random = new Random();

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

    @Test void mybatisTest1() throws IOException {
        Object users;

        // set resource path
        String resourcePath = "mybatis/mybatis-config.xml";

        try (InputStream resourceAsStream =
                     org.apache.ibatis.io.Resources.getResourceAsStream(resourcePath)) {

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

    @Test void findUserTest() {
        int id = 1;
        User result;

        try (SqlSession sqlSession = getSession(resourcePath)) {
            result = sqlSession
                    .getMapper(UserMapper.class)
                    .findUserById(id)
            ;
        }

        System.out.println(result);
    }

    @Test void saveUserTest() {

        List<User> users = new LinkedList<>();

        IntStream.range(0, 1000).forEach(i -> {
            users.add(new User(
                    null,
                    "嫦娥",
                    random.nextInt() >>> 1,
                    random.nextBoolean() ? "男" : "女"
            ));
        });

        try (SqlSession sqlSession = getSession(resourcePath)) {
            users.forEach(user -> {
                sqlSession
                        .getMapper(UserMapper.class)
                        .saveUser(user);
            });
        }
    }

    @Test void updateUserTest() {
        int id = 1;

        try (SqlSession sqlSession = getSession(resourcePath)) {
            User user = sqlSession
                    .getMapper(UserMapper.class)
                    .findUserById(id)
            ;

            user.setAge(random.nextInt() >>> 1);
            user.setSex(random.nextBoolean() ? "男" : "女");

            sqlSession
                    .getMapper(UserMapper.class)
                    .updateUser(user);

        }
    }

    @Test void deleteTest() {
        String name = "嫦娥";

        try (SqlSession sqlSession = getSession(resourcePath)) {

            User user = new User(null, name, null, null);

            sqlSession
                    .getMapper(UserMapper.class)
                    .deleteUser(user);
        }
    }

    @Test void mapTest() {
        Map<String, Integer> conditions = new HashMap<>();
        conditions.put("minAge", 100);
        conditions.put("maxAge", 10000);

        try (SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper.class)
                    .findByAge(conditions)
                    .forEach(System.out::println);
        }
    }

    @Test void paramTest() {
        List<User> result;
        try (SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper.class)
                    .findParam(100, 10000)
                    .forEach(System.out::println);
        }
    }

    @Test void likeTest() {
        String name = "乔";

        try (SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper.class)
                    .findByLike(name)
                    .forEach(System.out::println);
        }
    }

    @Test void contextLoads() { }

}
