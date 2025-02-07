package com.xxxxx.controller;

import com.xxxxx.anno.Log;
import com.xxxxx.pojo.Emp;
import com.xxxxx.pojo.EmpQueryParam;
import com.xxxxx.pojo.PageResult;
import com.xxxxx.pojo.Result;
import com.xxxxx.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//員工管理Controller
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //分頁查詢員工
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分頁查詢：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增員工
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增員工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //刪除員工(根據id刪除1筆或多筆員工資料)
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("刪除員工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //根據id查詢員工
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據id查詢員工資料：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    //修改員工
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改員工：{}", emp);
        empService.update(emp);
        return Result.success();
    }

}
