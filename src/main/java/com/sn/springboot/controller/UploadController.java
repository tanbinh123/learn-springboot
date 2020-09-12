package com.sn.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class UploadController {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest req) {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/img/") + format;
        File dest = new File(realPath);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        String name = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString().replaceAll("-", "") + name.substring(name.lastIndexOf("."));
        try {
            file.transferTo(new File(dest, newName));
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/img/" + format + "/" + newName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 多文件上传，如果是单个input则用数组接收，否则用多个MultipartFile
     * @param files
     * @param req
     * @return
     */
    @PostMapping("/uploads")
    public List<String> uploads(MultipartFile[] files, HttpServletRequest req) {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/img/") + format;
        File dest = new File(realPath);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            String newName = UUID.randomUUID().toString().replaceAll("-", "") + name.substring(name.lastIndexOf("."));
            try {
                file.transferTo(new File(dest, newName));
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/img/" + format + "/" + newName;
                urls.add(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }
}
