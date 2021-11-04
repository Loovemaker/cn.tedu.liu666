package cn.tedu.jt.ssm.controller;

import cn.tedu.jt.ssm.service.AxiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("axios")
public class AxiosController {
    @Autowired AxiosService service;
}
