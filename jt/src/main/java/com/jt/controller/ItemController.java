package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired ItemService service;

    @GetMapping("getItemList")
    public SysResult getItemList(PageResult pageResult) {
        pageResult = service.getItemList(pageResult);
        return SysResult.success(pageResult);
    }

    @PutMapping("updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item) {
        return service.updateItemStatus(item)
                ? SysResult.success()
                : SysResult.failed();
    }

    @DeleteMapping("deleteItemById")
    public SysResult deleteItemById(Integer id) {
        return service.deleteItemById(id)
                ? SysResult.success()
                : SysResult.failed();
    }

    @PostMapping("saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO) {
        return service.saveItem(itemVO)
                ? SysResult.success()
                : SysResult.failed();
    }
}
