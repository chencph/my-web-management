package com.xxxxx.service.impl;

import com.xxxxx.mapper.DeptMapper;
import com.xxxxx.pojo.Dept;
import com.xxxxx.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    //查詢所有部門的資料
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    //根據id刪除部門
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    //新增部門
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    //根據id查詢部門
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    //修改部門資料
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
