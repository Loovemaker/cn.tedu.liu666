package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired private ItemCatService service;

    @GetMapping("findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable String level) {
        val data = service.findItemCatList(Integer.valueOf(level));
        return Objects.nonNull(data) && !data.isEmpty()
                ? SysResult.success(data)
                : SysResult.failed();
    }
}
