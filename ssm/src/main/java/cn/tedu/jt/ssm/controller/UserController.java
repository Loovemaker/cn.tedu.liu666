package cn.tedu.jt.ssm.controller;


import cn.tedu.jt.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping("findall")
    public String findAll() {
        return userService.findAll().replaceAll("\n", "<br />");
    }
}
