package com.xxxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
//員工工作經歷
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpExpr {

    private Integer id;      //ID(primary key)
    private Integer empId;   //員工ID
    private LocalDate begin; //入職日期
    private LocalDate end;   //離職日期
    private String company;  //公司名稱
    private String job;      //職位

}
