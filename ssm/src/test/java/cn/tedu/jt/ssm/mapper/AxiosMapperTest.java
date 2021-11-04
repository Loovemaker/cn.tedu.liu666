package cn.tedu.jt.ssm.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AxiosMapperTest {

    @Autowired AxiosMapper mapper;

    @Test
    void findUserList() {
        mapper.findUserList().forEach(System.out::println);
    }
}