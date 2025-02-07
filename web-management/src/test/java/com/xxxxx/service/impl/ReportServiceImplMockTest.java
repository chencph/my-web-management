package com.xxxxx.service.impl;

import com.xxxxx.mapper.EmpMapper;
import com.xxxxx.pojo.Dept;
import com.xxxxx.pojo.JobOption;
import com.xxxxx.service.ReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ReportServiceImplMockTest {

    @MockitoBean
    private EmpMapper empMapper;

    @Autowired
    private ReportService reportService;

    @Test
    public void testGetEmpJobData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("pos", "行銷人員");
        map1.put("num", 6);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("pos", "業務人員");
        map2.put("num", 8);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("pos", "會計人員");
        map3.put("num", 2);
        Collections.addAll(list, map1, map2, map3);
        Mockito.when(empMapper.countEmpJobData()).thenReturn(list);

        JobOption result = reportService.getEmpJobData();

        verify(empMapper, times(1)).countEmpJobData();
        assertNotNull(result);
        assertEquals(3, result.getJobList().size());
        assertEquals(3, result.getDataList().size());
    }

    @Test
    public void testGetEmpGenderData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "男性");
        map1.put("value", 26);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "女性");
        map2.put("value", 28);
        Collections.addAll(list, map1, map2);
        Mockito.when(empMapper.countEmpGenderData()).thenReturn(list);

        List<Map<String, Object>> result = reportService.getEmpGenderData();

        verify(empMapper, times(1)).countEmpGenderData();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}