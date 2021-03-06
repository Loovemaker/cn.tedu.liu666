package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> findAll() {
        return mapper.selectList(null);
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
        val users = mapper.selectList(new QueryWrapper<User>()
                .eq("username", user.getUsername())
                .eq("password", user.getPassword())
        );

        // 判断
        if(!(Objects.nonNull(users) && !users.isEmpty())) return null;

        val uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    @Override
    public PageResult getUserList(PageResult pageResult) {

        val query = pageResult.getQuery();
        val page = mapper.selectPage(
                new Page<>(
                        pageResult.getPageNum(),
                        pageResult.getPageSize()
                ),
                new QueryWrapper<User>()
                        .like(StringUtils.hasLength(query), "username", query)
        );

        return pageResult
                .setTotal(page.getTotal())
                .setRows(page.getRecords());
    }

    @Override
    @Transactional
    public Boolean updateStatus(User user) {
        val rows = mapper.updateById(user);
        return Objects.equals(rows, 1);
    }

    @Override
    @Transactional
    public Boolean addUser(User user) {
        val defaultStatus = true;

        user
                .setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))
                .setStatus(defaultStatus);

        val rows = mapper.insert(user);
        return Objects.equals(rows, 1);
    }

    @Override
    public User getuserById(User user) {
        return mapper.selectById(user.getId());
    }

    @Override
    @Transactional
    public Boolean updateUser(User user) {
        val rows = mapper.updateById(user);
        return Objects.equals(1, rows);
    }

    @Override
    @Transactional
    public Boolean deleteUser(Integer id) {
        val rows = mapper.deleteById(id);
        return Objects.equals(1, rows);
    }

}
