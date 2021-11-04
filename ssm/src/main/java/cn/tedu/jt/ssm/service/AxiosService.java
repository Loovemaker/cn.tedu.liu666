package cn.tedu.jt.ssm.service;

import cn.tedu.jt.ssm.pojo.User;

import java.util.Set;

public interface AxiosService {
    Boolean saveUser(User user);

    Set<User> findUserList();
}
