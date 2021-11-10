package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    Date now() { return new Date(); }

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * Steps:
     * <ol>
     *     <li>加密password</li>
     *     <li>根据username/password获取数据</li>
     *     <li>
     *         根据查数据库，判断：
     *         <ul>
     *             <li>有数据：验证通过</li>
     *             <li>无数据：验证失败</li>
     *         </ul>
     *     </li>
     * </ol>
     * @param user  传过来的用户信息
     * @return  token值
     */
    @Override
    public String login(User user) {
        // 加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        // 获取
        User userDB = userMapper.findUserByUP(user);

        // 判断
        if(!Objects.nonNull(userDB)) return null;

        final UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    @Override
    public PageResult getUserList(PageResult pageResult) {
        Long total = userMapper.getTotal();

        Integer
                size = pageResult.getPageSize(),
                beginPage = size * (pageResult.getPageNum() - 1);
        List<User> rows = userMapper.findUserByPage(size, beginPage, pageResult.getQuery());

        return pageResult
                .setTotal(total)
                .setRows(rows);
    }

    @Override
    @Transactional
    public Boolean updateStatus(User user) {
        user.setUpdated(now());
        final Integer rows = userMapper.updateStatus(user);
        return Objects.equals(rows, 1);
    }

    @Override
    @Transactional
    public Boolean addUser(User user) {
        final Boolean defaultStatus = true;
        final Date now = now();

        user
                .setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))
                .setStatus(defaultStatus)
                .setCreated(now).setUpdated(now);

        final Integer rows = userMapper.addUser(user);
        return Objects.equals(rows, 1);
    }

    @Override
    public User getuserById(User user) {
        return userMapper.findUserById(user);
    }

    @Override
    @Transactional
    public Boolean updateUser(User user) {
        user.setUpdated(now());
        return Objects.equals(1, userMapper.updateUser(user));
    }

    @Override
    @Transactional
    public Boolean deleteUser(Integer id) {
        return Objects.equals(1, userMapper.deleteUser(id));
    }

}
