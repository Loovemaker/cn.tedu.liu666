package com.jt.service;

import com.jt.pojo.User;
import com.jt.vo.PageResult;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface UserService {

    List<User> findAll();

    String login(User user);

    PageResult getUserList(PageResult pageResult);

    Boolean updateStatus(User user);

    Boolean addUser(User user);
}
