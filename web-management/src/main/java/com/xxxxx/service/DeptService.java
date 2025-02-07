package com.xxxxx.service;

import com.xxxxx.pojo.Dept;

import java.util.List;

public interface DeptService {

    //查詢所有部門的資料
    List<Dept> findAll();

    //根據id刪除部門
    void deleteById(Integer id);

    //新增部門
    void add(Dept dept);

    //根據id查詢部門
    Dept getById(Integer id);

    //修改部門資料
    void update(Dept dept);
}
