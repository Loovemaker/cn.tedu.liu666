package cn.tedu.jt.ssm;

import cn.tedu.jt.ssm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SsmApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test void findAllTest() {
        System.out.println(userMapper.getClass());
        userMapper.findAll().forEach(System.out::println);
    }

}
