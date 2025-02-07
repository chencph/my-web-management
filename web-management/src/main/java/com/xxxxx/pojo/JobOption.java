package com.xxxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

//員工職位人數統計
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {

    private List<Object> jobList;  //職位列表
    private List<Object> dataList; //統計值列表

}
