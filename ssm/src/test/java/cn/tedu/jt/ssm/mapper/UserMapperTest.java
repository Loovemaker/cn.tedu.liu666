package cn.tedu.jt.ssm.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired UserMapper userMapper;

    @Test
    void findAll() {
        userMapper.findAll().forEach(System.out::println);
    }
}