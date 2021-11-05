package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }

    /**
     * http method: post
     * @param user username, password
     * @return  status, msg, data
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user) {
        String token = userService.login(user);

        return Objects.nonNull(token)
                ? SysResult.success(token)
                : SysResult.failed();
    }
}
