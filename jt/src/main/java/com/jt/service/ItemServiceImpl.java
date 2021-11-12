package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.PageResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired ItemMapper mapper;

    @Override
    public PageResult getItemList(PageResult pageResult) {
        val query = pageResult.getQuery();
        val page = mapper.selectPage(
                new Page<>(
                        pageResult.getPageNum(),
                        pageResult.getPageSize()
                ),
                new QueryWrapper<Item>()
                        .like(StringUtils.hasLength(query), "title", query)
        );
        return pageResult
                .setTotal(page.getTotal())
                .setRows(page.getRecords());
    }

    @Override
    @Transactional
    public Boolean updateItemStatus(Item item) {
        val rows = mapper.updateById(item);
        return rows == 1;
    }

    @Override
    @Transactional
    public Boolean deleteItemById(Integer id) {
        val rows = mapper.deleteById(id);
        return rows == 1;
    }


}
