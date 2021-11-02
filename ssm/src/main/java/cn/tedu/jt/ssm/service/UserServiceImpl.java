package cn.tedu.jt.ssm.service;

import cn.tedu.jt.ssm.mapper.UserMapper;
import cn.tedu.jt.ssm.pojo.User;
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

    @Override
    public String findUserById(Integer id) {
        return String.valueOf(userMapper.findUserById(id));
    }


    @Override
    public String findUserByIds(Integer[] ids) {
        final StringJoiner result = new StringJoiner("\n");
        userMapper.findUserByIds(ids).forEach(user -> result.add(user.toString()));
        return result.toString();
    }

    @Override
    public String findUserByAS(User user) {
        final StringJoiner result = new StringJoiner("\n");
        userMapper.findUserByAS(user).forEach(line -> result.add(line.toString()));
        return result.toString();
    }

    @Override
    public String updateUserById(User user) {
        return String.format(
                "%d rows affected",
                userMapper.updateUserById(user)
        );
    }

    @Override
    public String findUserContainName(String name) {
        final StringJoiner result = new StringJoiner("\n");
        userMapper.findUserContainName(name).forEach(line -> result.add(line.toString()));
        return result.toString();
    }

}
