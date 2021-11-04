package cn.tedu.jt.ssm.service;

import cn.tedu.jt.ssm.mapper.AxiosMapper;
import cn.tedu.jt.ssm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AxiosServiceImpl implements AxiosService {
    @Autowired AxiosMapper mapper;

    @Override
    public Boolean saveUser(User user) {
        return mapper.saveUser(user) != 0;
    }

    @Override
    public List<User> findUserList() {
        return mapper.findUserList();
    }

    @Override
    public Integer updateUser(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return mapper.deleteUser(id);
    }
}
