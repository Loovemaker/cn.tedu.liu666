package com.jt.service;

import com.jt.pojo.User;
import com.jt.vo.PageResult;

import java.util.List;

public interface UserService {

    List<User> findAll();

    String login(User user);

    PageResult getUserList(PageResult pageResult);
}
