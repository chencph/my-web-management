package com.xxxxx.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

//員工查詢頁面的查詢參數
@Data
public class EmpQueryParam {

    private Integer page = 1;      //頁碼
    private Integer pageSize = 10; //每頁展示資料筆數
    private String name;    //員工姓名
    private Integer gender; //員工性別
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //員工入職日期(開始)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;   //員工入職日期(結束)

}
