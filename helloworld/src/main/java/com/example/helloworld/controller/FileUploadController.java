package com.example.helloworld.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String up(String nickanme, MultipartFile photo, HttpServletRequest request) throws IOException
    {
    System.out.println();
    //获取图片原始名称
    System.out.println(photo.getOriginalFilename());
    //文件类型
     System.out.println(photo.getContentType());
    //动态获取路径
     String path = request.getServletContext().getRealPath("/upload");
     System.out.println(path);
     //自建方法
     saveFile(photo,path);
     return "上传成功";
    }
    //
    public void saveFile(MultipartFile photo,String path) throws IOException
    //判断存储目录是否存在，不存在则创建
    {
        File dir =new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file =new File(path,photo.getOriginalFilename());
        photo.transferTo(file);
        //访问在properties加入spring.web.resources.static-locations=/upload/
    }

}
