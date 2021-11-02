package cn.tedu.jt.spring.demo3;

import cn.tedu.jt.spring.demo3.mapper.EmpMapper;
import cn.tedu.jt.spring.demo3.pojo.Emp;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;


class Demo3ApplicationTests {

    String resourcePath = "mybatis/mybatis-config.xml";

    SqlSessionFactory getSessionFactory(String resourcePath) {
        try (InputStream resourceAsStream = org.apache.ibatis.io.Resources.getResourceAsStream(resourcePath)) {

            // build SQL session factory
            return new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Test void testCache1() {
        List<Set<Emp>> list = new ArrayList<>();

        try (final SqlSession session = getSessionFactory(resourcePath).openSession(true)) {
            final EmpMapper mapper = session.getMapper(EmpMapper.class);
            IntStream.range(0, 100).forEach(i -> {
                list.add(mapper.findAll());
            });
        }
    }

    @Test void testCache2() {
        final SqlSessionFactory sessionFactory = getSessionFactory(resourcePath);
        IntStream.range(0, 100).forEach(i -> {
            try (final SqlSession session = sessionFactory.openSession(true)) {
                session
                        .getMapper(EmpMapper.class)
                        .findAll()
                        .forEach(System.out::println);
            }
        });
    }

}
