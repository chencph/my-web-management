package com.xxxxx.controller;

import com.xxxxx.pojo.JobOption;
import com.xxxxx.pojo.Result;
import com.xxxxx.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.List;

//統計資料Controller
@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    //統計員工職位人數
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("統計員工職位人數");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    //統計員工性別人數
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("統計員工性別人數");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

}
