package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired private ItemCatMapper mapper;
    final QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();

    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        val ROOT_PARENT = 0;
        return fillChildren(getAllItemCatMapByParentId(), level)
                .get(ROOT_PARENT);
    }

    @Override
    public Boolean updateStatus(Integer id, Boolean status) {
        val rows = mapper.updateById(new ItemCat()
                .setId(id)
                .setStatus(status)
        );
        return Objects.equals(1, rows);
    }

    Map<Integer, List<ItemCat>> getAllItemCatMapByParentId() {
        val parentIdMap = new HashMap<Integer, List<ItemCat>>();
        mapper.selectList(null).forEach(itemCat -> {
            val parentId = itemCat.getParentId();
            if (!parentIdMap.containsKey(parentId))
                parentIdMap.put(parentId, new LinkedList<>());
            parentIdMap.get(parentId).add(itemCat);
        });
        return parentIdMap;
    }

    Map<Integer, List<ItemCat>> fillChildren(
            final Map<Integer, List<ItemCat>> target,
            Integer maxLevel
    ) {
        if (!(maxLevel > 0)) return null;

        val ROOT_PARENT = 0;
        target.get(ROOT_PARENT).forEach(itemCat ->
            fillChildren(target, itemCat, maxLevel)
        );
        return target;
    }

    void fillChildren(
            final Map<Integer, List<ItemCat>> target,
            ItemCat itemCat,
            Integer maxLevel
    ) {
        if (!(itemCat.getLevel() < maxLevel)) return;

        val children = target.get(itemCat.getId());
        if (!children.isEmpty()) {
            children.forEach(itemCat1 -> {
                itemCat.setChildren(children);
                fillChildren(target, itemCat1, maxLevel);
            });
        }
    }
}
