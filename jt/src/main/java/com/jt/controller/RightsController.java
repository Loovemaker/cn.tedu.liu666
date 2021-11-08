package com.jt.controller;

import com.jt.service.RightsService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rights")
@CrossOrigin
public class RightsController {
    @Autowired private RightsService service;

    /**
     * 查询一级二级菜单项
     * @return  含有菜单项数据的SysResult包
     */
    @GetMapping("/getRightsList")
    public SysResult getRightsList() {
        return SysResult.success(service.getRightsList());
    }
}
