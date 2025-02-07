package com.xxxxx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xxxxx.mapper.EmpExprMapper;
import com.xxxxx.mapper.EmpMapper;
import com.xxxxx.pojo.*;
import com.xxxxx.service.EmpService;
import com.xxxxx.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    //分頁查詢(基於PageHelper)
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize()); //(page：頁碼，pageSize：每頁展示的資料筆數)
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>)empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    //新增員工
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        //新增員工基本資料
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //使用MD5生成密碼的雜湊值
        if(emp.getPassword() != null && !emp.getPassword().isEmpty()){
            String hashPassword = DigestUtils.md5DigestAsHex(emp.getPassword().getBytes());
            emp.setPassword(hashPassword);
        }
        empMapper.insert(emp);
        //新增員工工作經歷
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    //刪除員工
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //刪除員工基本資料
        empMapper.deleteByIds(ids);
        //刪除員工工作經歷
        empExprMapper.deleteByEmpIds(ids);
    }

    //根據id查詢員工
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    //修改員工
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //根據id修改員工基本資料
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //根據員工id修改員工工作經歷(先刪除再新增)
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    //員工登入
    @Override
    public LoginInfo login(Emp emp) {
        //使用MD5生成密碼的雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(emp.getPassword().getBytes());
        emp.setPassword(hashPassword);
        //根據username和password查詢員工資料
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //若username和password正確，返回LogonInfo；若不正確，返回null
        if(e != null){
            log.info("登入成功，員工資料：{}", e);
            //產生JWT憑證
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }
        return null;
    }

}
