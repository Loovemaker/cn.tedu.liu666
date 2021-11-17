package com.jt.controller;


import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired FileService service;

    @PostMapping("upload")
    public SysResult upload(MultipartFile file) throws IOException {
        val data = service.upload(file);
        return SysResult.success(data);
    }

    @DeleteMapping ("deleteFile")
    public SysResult delete(ImageVO imageVO) throws IOException {
        service.delete(imageVO.getVirtualPath());
        return SysResult.success();
    }
}
