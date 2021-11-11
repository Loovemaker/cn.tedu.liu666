package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.misc.MybatisPlusUtils;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired private ItemCatMapper mapper;
    final QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();

    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        if (!(level > 0)) return null;

        AtomicReference<List<ItemCat>> itemCatsWithoutChildren = new AtomicReference<>();
        MybatisPlusUtils.wrapperExecution(queryWrapper, itemCatWrapper ->
            itemCatsWithoutChildren.set(mapper.selectList(queryWrapper
                    .eq("level", 1)
                    .eq("parent_id", 0)
            ))
        );
        return fillItemCatChildren(itemCatsWithoutChildren.get(), level);
    }

    List<ItemCat> fillItemCatChildren(List<ItemCat> target, Integer maxLevel) {
        target.forEach(item -> {
            if (!(item.getLevel() < maxLevel)) return;

            AtomicReference<List<ItemCat>> children = new AtomicReference<>();
            MybatisPlusUtils.wrapperExecution(queryWrapper, itemCatWrapper ->
                    children.set(mapper.selectList(queryWrapper
                            .eq("level", item.getLevel() + 1)
                            .eq("parent_id", item.getId())
                    ))
            );

            if (!children.get().isEmpty()) {
                fillItemCatChildren(children.get(), maxLevel);
                item.setChildren(children.get());
            }
        });
        return target;
    }
}
