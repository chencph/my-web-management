package com.xxxxx.service;

import com.xxxxx.pojo.Emp;
import com.xxxxx.pojo.EmpQueryParam;
import com.xxxxx.pojo.LoginInfo;
import com.xxxxx.pojo.PageResult;

import java.util.List;

public interface EmpService {

    //分頁查詢
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增員工
    void save(Emp emp);

    //刪除員工
    void delete(List<Integer> ids);

    //根據id查詢員工
    Emp getInfo(Integer id);

    //修改員工
    void update(Emp emp);

    //員工登入
    LoginInfo login(Emp emp);
}
