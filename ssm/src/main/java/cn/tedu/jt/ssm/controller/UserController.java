package cn.tedu.jt.ssm.controller;


import cn.tedu.jt.ssm.pojo.User;
import cn.tedu.jt.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping("findall")
    public String findAll() {
        return userService.findAll().replaceAll("\n", "<br />\n");
    }

    @RequestMapping("find/byid/{id}")
    public String findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping("find/byids")
    public String findUserByIds(Integer[] ids) {
        return userService.findUserByIds(ids).replaceAll("\n", "<br />\n");
    }

    @RequestMapping("find/byas")
    public String findUserByAS(User user) {
        return userService.findUserByAS(user).replaceAll("\n", "<br />\n");
    }

    @RequestMapping("update/byid/{id}/{name}/{age}/{sex}")
    public String updateUserById(User user) {
        return userService.updateUserById(user);
    }

    @RequestMapping("find/containname/{name}")
    public String findUserContainName(@PathVariable String name) {
        return userService.findUserContainName(name).replaceAll("\n", "<br />\n");
    }


}
