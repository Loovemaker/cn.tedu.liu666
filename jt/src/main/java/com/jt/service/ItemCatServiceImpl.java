package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import jdk.jfr.internal.EventWriterMethod;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired private ItemCatMapper mapper;
    final QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();

    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        if (!(level > 0)) return null;

        List<ItemCat> itemCatWithoutChildren;
        synchronized (queryWrapper) {
            queryWrapper.clear();
            itemCatWithoutChildren = mapper.selectList(queryWrapper
                    .eq("level", 1)
                    .eq("parent_id", 0)
            );
        }
        
        return fillItemCatChildren(itemCatWithoutChildren, level);
    }

    List<ItemCat> fillItemCatChildren(List<ItemCat> target, Integer maxLevel) {
        target.forEach(item -> {
            if (!(item.getLevel() < maxLevel)) return;

            List<ItemCat> children;
            synchronized (queryWrapper) {
                queryWrapper.clear();
                children = mapper.selectList(queryWrapper
                        .eq("level", item.getLevel() + 1)
                        .eq("parent_id", item.getId())
                );
            }

            if (!children.isEmpty()) {
                fillItemCatChildren(children, maxLevel);
                item.setChildren(children);
            }
        });
        return target;
    }
}
