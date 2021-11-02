package cn.tedu.jt.ssm.service;

import cn.tedu.jt.ssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;


@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserMapper userMapper;

    @Override
    public String findAll() {
        final StringJoiner result = new StringJoiner("\n");
        userMapper.findAll().forEach(user -> result.add(user.toString()));
        return result.toString();
    }
}
