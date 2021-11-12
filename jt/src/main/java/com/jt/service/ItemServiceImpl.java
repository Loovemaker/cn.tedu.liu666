package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired ItemMapper itemMapper;
    @Autowired ItemDescMapper itemDescMapper;

    @Override
    public PageResult getItemList(PageResult pageResult) {
        val query = pageResult.getQuery();
        val page = itemMapper.selectPage(
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
        val rows = itemMapper.updateById(item);
        return rows == 1;
    }

    @Override
    @Transactional
    public Boolean deleteItemById(Integer id) {
        val itemRows = itemMapper.deleteById(id);
        val itemDescRows = itemDescMapper.deleteById(id);
        return itemRows == 1 || itemDescRows == 1;
    }

    @Override
    @Transactional
    public Boolean saveItem(ItemVO itemVO) {
        val item = itemVO.getItem()
                .setStatus(true);
        val itemRows = itemMapper.insert(item);

        val itemDesc = itemVO.getItemDesc()
                .setId(item.getId());
        val itemDescRows = itemDescMapper.insert(itemDesc);
        return itemRows == 1 && itemDescRows == 1;
    }


}
