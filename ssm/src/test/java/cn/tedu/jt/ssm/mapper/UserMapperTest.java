package cn.tedu.jt.ssm.mapper;

import cn.tedu.jt.ssm.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
class UserMapperTest {

    @Autowired UserMapper userMapper;

    Random random = new Random();

    @Test
    void findAll() {
        userMapper.findAll().forEach(System.out::println);
    }

    @Test
    void findUserById() {
        System.out.println(userMapper.findUserById(1));
    }

    @Test
    void findUserByAS() {
        userMapper.findUserByAS(new User(null, null, 2000, "男"))
                .forEach(System.out::println);
    }

    @Test
    void updateUserById() {
        userMapper.updateUserById(new User(
                1,
                "黑熊精",
                random.nextInt() >>> 1,
                random.nextBoolean() ? "男" : "女"
        ));
    }

    @Test
    void findUserContainName() {
        userMapper.findUserContainName("乔")
                .forEach(System.out::println);
    }

    @Test
    void findUserByIds() {
        int length = 50;
        Integer[] integers = new Integer[length];
        IntStream.range(0, length).forEach(i -> integers[i] = i);

        userMapper
                .findUserByIds(integers)
                .forEach(System.out::println);
    }
}