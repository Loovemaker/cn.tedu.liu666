package cn.tedu.jt.ssm.service;


import cn.tedu.jt.ssm.pojo.User;

public interface UserService {
    String findAll();

    String findUserById(Integer id);

    String findUserByIds(Integer[] ids);

    String findUserByAS(User user);

    String updateUserById(User user);

    String findUserContainName(String name);

}
