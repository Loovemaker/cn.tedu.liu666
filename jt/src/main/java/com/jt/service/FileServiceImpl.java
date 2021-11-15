package com.jt.service;

import com.jt.vo.ImageVO;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    static final String homePath = System.getProperty("user.home");
    static final String dirPathFromHome = "Pictures/tedu";
    static final String delimiter = "/";
    static final Integer prefixCount = 2;
    static final Set<String> validFilenameExtensions = new HashSet<>(Arrays.asList
            ("jpg", "jpeg", "png", "gif", "tif", "tiff", "bmp", "heic")
    );

    @Override
    @Transactional
    public ImageVO upload(MultipartFile file) throws IOException {

        // validate filename extension
        val extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
//        if (!validFilenameExtensions.contains(extension))
//                throw new java.lang.IllegalArgumentException();

        // validate file content
        if (!isPicture(file.getInputStream())) throw new java.lang.IllegalArgumentException();

        // write file
        val md5DigestAsHex = DigestUtils.md5DigestAsHex(file.getBytes());

        val fileName = md5DigestAsHex + "." + extension;

        val virtualPath = new StringJoiner(delimiter)
                .add(dirPathFromHome)
                .add(fileName.substring(0, prefixCount))
                .toString();

        val destinationDir = new File(
                new StringJoiner(delimiter)
                        .add(homePath)
                        .add(virtualPath)
                        .toString()
        );

        if (!destinationDir.exists())
            if(!destinationDir.mkdirs())
                throw new IOException("mkdir failed");

        val destinationPath = new StringJoiner(delimiter)
                .add(destinationDir.getAbsolutePath())
                .add(fileName)
                .toString();

        file.transferTo(Paths.get(destinationPath));

        // return view object

        return new ImageVO(
                destinationPath,
                "",
                fileName
        );
    }

    public boolean isPicture(InputStream stream) throws IOException {
        val picture = ImageIO.read(stream);
        if (!Objects.nonNull(picture)) return false;

        val width = picture.getWidth();
        val height = picture.getHeight();
        return width > 0 && height > 0;
    }
}
