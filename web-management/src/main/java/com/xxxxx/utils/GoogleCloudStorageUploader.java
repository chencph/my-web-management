package com.xxxxx.utils;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

//將員工的照片上傳至google cloud storage，並返回該照片的url
@Slf4j
@Component
public class GoogleCloudStorageUploader {

    @Value("${google.cloud.storage.bucketName}")
    private String bucketName;

    public String upload(byte[] content, String originalFileName) {
        //初始化Storage用戶端
        Storage storage = StorageOptions.getDefaultInstance().getService();

        String fileUrl = null;

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content)) {
            //定義blobName, BlobId和BlobInfo
            String blobName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
            BlobId blobId = BlobId.of(bucketName, blobName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            //上傳檔案
            storage.create(blobInfo, byteArrayInputStream);

            fileUrl = "https://storage.googleapis.com/" + bucketName + "/" + blobName;

        } catch (IOException e) {
            log.info("檔案上傳失敗：" + e.getMessage());
        }

        return fileUrl;
    }

}