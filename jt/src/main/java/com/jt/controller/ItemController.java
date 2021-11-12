package com.jt.controller;

import com.jt.service.ItemService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
