package com.jt.controller;


import com.jt.service.FileService;
import com.jt.vo.SysResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;


@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired FileService service;



    @PostMapping("upload")
    public SysResult upload(MultipartFile file) throws IOException {
        service.upload(file);
        return SysResult.success();
    }
}
