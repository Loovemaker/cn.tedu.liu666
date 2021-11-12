package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import lombok.val;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired private ItemCatMapper mapper;

    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        val ROOT_PARENT = 0;
        return fillChildren(getAllItemCatMapByParentId(), level)
                .get(ROOT_PARENT);
    }

    @Override
    @Transactional
    public Boolean updateStatus(Integer id, Boolean status) {
        val rows = mapper.updateById(new ItemCat()
                .setId(id)
                .setStatus(status)
        );
        return Objects.equals(1, rows);
    }

    @Override
    @Transactional
    public Boolean saveItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
        val parent = mapper.selectById(itemCat.getParentId());

        // check if is parent
        Assert.isTrue(
                Objects.equals(parent.getId(), itemCat.getParentId()),
                "Error parameter of input: itemcat.id"
        );
        Assert.isTrue(
                Objects.equals(parent.getLevel() + 1, itemCat.getLevel()),
                "Error parameter of input: itemcat.level"
        );

        val rows = mapper.insert(itemCat);
        return Objects.equals(1, rows);
    }

    @Override
    @Transactional
    public Boolean updateItemCat(ItemCat itemCat) {
        val rows = mapper.updateById(itemCat);
        return Objects.equals(1, rows);
    }

    @Override
    @Transactional
    public Boolean deleteItemCat(ItemCat itemCat) {
        var rows = 0;

        List<Integer> targetIds = new LinkedList<>();
        targetIds.add(itemCat.getId());

        while (!targetIds.isEmpty()) {
            rows += mapper.deleteBatchIds(targetIds);

            List<Integer> newTargetIds = new LinkedList<>();
            mapper.selectObjs(new QueryWrapper<ItemCat>()
                    .in("parent_id", targetIds)
            ).forEach(o -> newTargetIds.add((Integer) o));
            targetIds = newTargetIds;
        }

        return rows != 0;
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

        var children = target.get(itemCat.getId());
        if (Objects.isNull(children)) children = new LinkedList<>();
        if (!children.isEmpty()) {
            List<ItemCat> finalChildren = children;
            children.forEach(itemCat1 -> {
                itemCat.setChildren(finalChildren);
                fillChildren(target, itemCat1, maxLevel);
            });
        }
    }
}
