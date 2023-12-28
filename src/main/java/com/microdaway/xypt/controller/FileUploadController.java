package com.microdaway.xypt.controller;

import com.microdaway.xypt.entity.Result;
import com.microdaway.xypt.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/uploadAvatar")
    public Result<String> upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        // 将文件的内容存储到本地磁盘上
        if (multipartFile != null) {
            String originalFilename = multipartFile.getOriginalFilename();
            // 保证文件的名字是唯一的 从而防止文件覆盖
            String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            String url = AliOssUtil.uploadFile(filename, multipartFile.getInputStream());
            return Result.success(url);
        }

        return Result.error("出错了");
    }
}
