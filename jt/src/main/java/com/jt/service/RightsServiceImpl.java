package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RightsServiceImpl implements RightsService{
    @Autowired private RightsMapper mapper;

    @Override
    public List<Rights> getRightsList() {
        val ROOT_CHILDREN = 0;
        val users = mapper.selectList(new QueryWrapper<Rights>()
                .eq("parent_id", ROOT_CHILDREN)
        );
        users.forEach(user ->
            user.setChildren(mapper.selectList(new QueryWrapper<Rights>()
                    .eq("parent_id", user.getId())
            ))
        );
        return users;
    }
}
