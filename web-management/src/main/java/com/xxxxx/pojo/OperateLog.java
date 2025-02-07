package com.xxxxx.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperateLog {

    private Integer id; //ID
    private Integer operateEmpId; //操作者ID
    private LocalDateTime operateTime; //操作時間
    private String className; //操作類別名稱
    private String methodName; //操作方法名稱
    private String methodParams; //操作方法參數
    private String returnValue; //操作方法返回值
    private  Long costTime; //操作耗時

}
