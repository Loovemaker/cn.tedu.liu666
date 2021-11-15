package com.jt.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

@Service
public class FileServiceImpl implements FileService {

    static final String homePath = System.getProperty("user.home");
    static final String dirPathFromHome = "Pictures/tedu";

    @Override
    public void upload(MultipartFile file) throws IOException {
        val dir = new File(
                new StringJoiner("/")
                        .add(homePath)
                        .add(dirPathFromHome)
                        .toString()
        );
        if (!dir.exists()) if(!dir.mkdirs()) throw new IOException();

        val fileName = DigestUtils.md5DigestAsHex(file.getBytes());
        val destinationFile = new File(
                new StringJoiner("/")
                        .add(homePath)
                        .add(dirPathFromHome)
                        .add(fileName)
                        .toString()
        );

        file.transferTo(destinationFile);
    }
}
