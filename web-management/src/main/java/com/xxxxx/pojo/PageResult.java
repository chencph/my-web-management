package com.xxxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

//分頁查詢結果
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private Long total;   //總資料筆數
    private List<T> rows; //當前頁面展示的資料

}
