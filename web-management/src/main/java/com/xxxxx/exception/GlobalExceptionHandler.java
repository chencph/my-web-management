package com.xxxxx.exception;

import com.xxxxx.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全域的例外處理策略
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //統一的例外處理
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程式出現錯誤", e);
        return Result.error("出現錯誤，請重新嘗試。");
    }

    //違反unique約束的例外處理
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程式出現錯誤", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在，請輸入其他內容。");
    }

}
