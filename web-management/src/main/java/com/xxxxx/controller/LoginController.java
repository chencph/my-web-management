package com.xxxxx.controller;

import com.xxxxx.pojo.Emp;
import com.xxxxx.pojo.LoginInfo;
import com.xxxxx.pojo.Result;
import com.xxxxx.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//登入Controller
@Slf4j
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    //員工登入
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("登入：{}", emp);
        LoginInfo info = empService.login(emp);
        if(info != null){
            return Result.success(info);
        }
        return Result.error("使用者名稱或密碼不正確");
    }

}
