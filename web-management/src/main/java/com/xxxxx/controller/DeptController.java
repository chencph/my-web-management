package com.xxxxx.controller;

import com.xxxxx.anno.Log;
import com.xxxxx.pojo.Dept;
import com.xxxxx.pojo.Result;
import com.xxxxx.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j //作用等於：private static final Logger log = LoggerFactory.getLogger(DeptController.class);
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查詢所有部門的資料
    @GetMapping
    public Result list(){
        log.info("查詢所有部門的資料");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //根據id刪除部門
    @Log
    @DeleteMapping
    public Result delete(/*@RequestParam(value = "id", required = false)*/Integer id){
        log.info("根據ID刪除部門：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部門
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){  //@RequestBody：接收json格式的請求參數
        log.info("新增部門：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    //根據id查詢部門
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據id查詢部門{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部門資料
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部門資料：{}", dept);
        deptService.update(dept);
        return Result.success();
    }


}
