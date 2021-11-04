package cn.tedu.jt.ssm.controller;

import cn.tedu.jt.ssm.pojo.User;
import cn.tedu.jt.ssm.service.AxiosService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("axios")
public class AxiosController {
    @Autowired AxiosService service;


    @PostMapping("add-user")
    public String saveUser(@RequestBody User user) {
        return "" + (service.saveUser(user) ? "" : "not ") + "affected";
    }

    @GetMapping("find-user-list")
    public List<User> findUserList() {
        return service.findUserList();
    }

    @PutMapping("update-user")
    public Integer updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("delete-user")
    public Integer deleteUser(@RequestParam Integer id) {
        return service.deleteUser(id);
    }
}
