package com.xxxxx.controller;

import com.xxxxx.pojo.Result;
import com.xxxxx.utils.GoogleCloudStorageUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

//將新增的員工照片上傳至google cloud storage，並返回該照片的url
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private GoogleCloudStorageUploader googleCloudStorageUploader;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("檔案上傳：{}", file.getOriginalFilename());
        String url = googleCloudStorageUploader.upload(file.getBytes(), file.getOriginalFilename());
        log.info("檔案上傳成功，url：" + url);
        return Result.success(url);
    }

}
