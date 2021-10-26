package cn.tedu.jt.spring.demo1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequestMapping("/")
@PropertySource(value = "classpath:/hello.properties", encoding = "utf-8")
public class HelloController {

    @Value("${cn.tedu.jt.spring.demo1.hello}")
    private String helloworld;

    @RequestMapping("hello")
    public String hello() {
        System.out.println(helloworld);
        return helloworld;
    }
}
