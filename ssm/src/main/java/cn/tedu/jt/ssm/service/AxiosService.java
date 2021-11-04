package cn.tedu.jt.ssm.service;

import cn.tedu.jt.ssm.pojo.User;

import java.util.List;

public interface AxiosService {
    Boolean saveUser(User user);

    List<User> findUserList();
}
