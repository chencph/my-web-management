package com.xxxxx.pojo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//員工資料
@Data
public class Emp {

    private Integer id;      //ID(primary key)
    private String username; //用戶名
    private String password; //密碼
    private String name;     //姓名
    private Integer gender;  //性別 1:男 2:女
    private String phone;    //電話號碼
    private Integer job;     //職位 1:行政人員 2:業務 ...
    private Integer salary;  //薪資
    private String image;    //照片
    private LocalDate entryDate; //入職日期
    private Integer deptId;     //所屬部門ID
    private LocalDateTime createTime;  //資料創建時間
    private LocalDateTime updateTime;  //資料更新時間

    //部門名稱
    private String deptName;

    //工作經歷
    private List<EmpExpr> exprList;

}
