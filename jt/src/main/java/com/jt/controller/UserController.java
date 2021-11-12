package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

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

    /**<p>用户列表展现</p>
     * <p>请求案例: <code>http://localhost:8091/user/list?query=查询关键字&pageNum=1&pageSize=10</code></p>
     * <p>http method: get</p>
     * @return  含有
     */
    @GetMapping("list")
    public SysResult getUserList(PageResult pageResult) {
        pageResult = userService.getUserList(pageResult);
        return SysResult.success(pageResult);
    }

    /**
     * 用户状态修改
     * <p>http method: put</p>
     * @return  应该为success
     */
    @PutMapping("status/{id}/{status}")
    public SysResult updateStatus(User user) {
        return userService.updateStatus(user)
                ? SysResult.success()
                : SysResult.failed();
    }

    /**
     * 用户新增
     * <p>http method: post</p>
     * @return 应该为success
     */
    @PostMapping("addUser")
    public SysResult addUser(@RequestBody User user) {
        return userService.addUser(user)
                ? SysResult.success()
                : SysResult.failed();
    }

    /**
     *  根据ID查询用户信息
     *  <p>用户修改</p>
     */
    @GetMapping("{id}")
    public SysResult updateUsergetuserById(User user) {
        return SysResult.success(userService.getuserById(user));
    }

    @PutMapping("updateUser")
    public SysResult updateUser(@RequestBody User user) {
        return userService.updateUser(user)
                ? SysResult.success()
                : SysResult.failed();
    }

    /**
     * 根据ID删除用户
     */
    @DeleteMapping("{id}")
    public SysResult deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id)
                ? SysResult.success()
                : SysResult.failed();
    }
}
