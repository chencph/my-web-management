package com.xxxxx.service;

import com.xxxxx.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    //統計員工職位人數
    JobOption getEmpJobData();

    //統計員工性別人數
    List<Map<String, Object>> getEmpGenderData();
}
