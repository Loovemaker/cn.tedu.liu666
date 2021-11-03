package cn.tedu.jt.ssm.controller;


import cn.tedu.jt.ssm.pojo.User;
import cn.tedu.jt.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("findall")
    public String findAll() {
        return userService.findAll().replaceAll("\n", "<br />\n");
    }

    @GetMapping("find/byid/{id}")
    public String findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("find/byids")
    public String findUserByIds(Integer[] ids) {
        return userService.findUserByIds(ids).replaceAll("\n", "<br />\n");
    }

    @GetMapping("find/byas")
    public String findUserByAS(User user) {
        return userService.findUserByAS(user).replaceAll("\n", "<br />\n");
    }

    @PutMapping(value = "update/byid/{id}/{name}/{age}/{sex}")
    public String updateUserById(User user) {
        return userService.updateUserById(user);
    }

    @RequestMapping("find/containname/{name}")
    public String findUserContainName(@PathVariable String name) {
        return userService.findUserContainName(name).replaceAll("\n", "<br />\n");
    }


}
