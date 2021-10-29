package cn.tedu.mybatis.demo2;

import cn.tedu.mybatis.demo2.mapper.UserMapper2;
import cn.tedu.mybatis.demo2.pojo.User2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.stream.IntStream;

public class TestMybatis {

    String resourcePath = "mybatis/mybatis-config.xml";

    Random random = new Random();

    String randomGender() {
        return random.nextBoolean() ? "男" : "女";
    }

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

    // TODO: Find out WHY ALIAS functionality in the fuxxin xml cunt FULLY work wotule
    @Test void findUser() {
        try (final SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper2.class)
                    .listAllUsers()
                    .forEach(System.out::println);
        }
    }

    @Test void findInTest() {
        final int[] ids = IntStream.range(0, 100).toArray();

        try (final SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper2.class)
                    .findIn(ids)
                    .forEach(System.out::println);
        }
    }

    @Test void findInMapTest() {
        final int[] ids = IntStream.range(0, 100).toArray();
        String male = "男";

        try (final SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper2.class)
                    .findInMap(ids, male)
                    .forEach(System.out::println);
        }
    }

    @Test void findSqlWhereTest() {
        final User2 user2 = new User2(null, "金角大王", 3000, "男");
        try (final SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper2.class)
                    .findSqlWhere(user2)
                    .forEach(System.out::println);
        }
    }

    @Test void sqlUpdateTest() {
        final User2 守山使者 = new User2(4, "守山使者", random.nextInt() >>> 1, null);

        try (final SqlSession sqlSession = getSession(resourcePath)) {
            System.out.printf("%d rows affected\n", sqlSession
                    .getMapper(UserMapper2.class)
                    .sqlUpdate(守山使者));
        }
    }

    @Test void findChooseTest() {
        final User2 user2 = new User2(null, null, 500, null);

        try (final SqlSession sqlSession = getSession(resourcePath)) {
            sqlSession
                    .getMapper(UserMapper2.class)
                    .findChoose(user2)
                    .forEach(System.out::println);
        }
    }
}
