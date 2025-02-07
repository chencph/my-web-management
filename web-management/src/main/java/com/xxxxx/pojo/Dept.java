package com.xxxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
//部門資料
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;  //ID(primary key)
    private String name; //部門名稱
    private LocalDateTime createTime; //資料創建時間
    private LocalDateTime updateTime; //資料更新時間
}
