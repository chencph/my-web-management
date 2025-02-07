package com.xxxxx.service.impl;

import com.github.pagehelper.Page;
import com.xxxxx.mapper.EmpExprMapper;
import com.xxxxx.mapper.EmpMapper;
import com.xxxxx.pojo.*;
import com.xxxxx.service.EmpService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class EmpServiceImplMockTest {

    @MockitoBean
    private EmpMapper empMapper;

    @MockitoBean
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpService empService;

    @Test
    public void testPage() {
        EmpQueryParam empQueryParam = new EmpQueryParam();
        empQueryParam.setPage(1);
        empQueryParam.setPageSize(10);
        empQueryParam.setName("黃");

        Page<Emp> page = new Page<>();
        Emp emp = new Emp();
        emp.setId(1);
        emp.setName("黃惠娟");
        emp.setGender(2);
        emp.setUsername("Becky");
        emp.setPhone("0918273928");
        emp.setJob(3);
        emp.setSalary(50000);
        emp.setImage("01.jpg");
        emp.setEntryDate(LocalDate.parse("2018-05-05"));
        emp.setDeptId(3);
        emp.setCreateTime(LocalDateTime.parse("2017-12-03T10:15:30"));
        emp.setUpdateTime(LocalDateTime.parse("2025-02-03T11:17:21"));
        page.add(emp);

        Mockito.when(empMapper.list(any())).thenReturn(page);

        PageResult<Emp> result = empService.page(empQueryParam);

        assertNotNull(result);
        assertEquals(page.getTotal(), result.getTotal());
        assertEquals(page.getResult(), result.getRows());
    }

    @Test
    public void testSave() {
        Emp emp = new Emp();
        emp.setId(1);
        emp.setName("羅慧娟");
        List<EmpExpr> empExprList = new ArrayList<EmpExpr>();
        EmpExpr empExpr1 = new EmpExpr(null, 1, LocalDate.parse("2011-07-09"), LocalDate.parse("2017-04-06"), "A公司", "行銷專員");
        EmpExpr empExpr2 = new EmpExpr(null, 1, LocalDate.parse("2017-06-26"), LocalDate.parse("2023-05-08"), "B公司", "行銷專員");
        Collections.addAll(empExprList, empExpr1, empExpr2);
        emp.setExprList(empExprList);

        empService.save(emp);

        verify(empMapper, times(1)).insert(emp);
        verify(empExprMapper, times(1)).insertBatch(emp.getExprList());
    }

    @Test
    public void testDelete() {
        List<Integer> ids = new ArrayList<>();
        Collections.addAll(ids, 1, 2, 3);

        empService.delete(ids);

        verify(empMapper, times(1)).deleteByIds(ids);
        verify(empExprMapper, times(1)).deleteByEmpIds(ids);
    }

    @Test
    public void testGetInfo() {
        Emp emp = new Emp();
        emp.setId(1);
        emp.setName("羅慧娟");
        Mockito.when(empMapper.getById(any())).thenReturn(emp);

        Emp result = empService.getInfo(1);

        verify(empMapper, times(1)).getById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("羅慧娟", result.getName());
    }

    @Test
    public void testUpdate() {
        Emp emp = new Emp();
        emp.setId(1);
        emp.setName("羅慧娟");
        List<EmpExpr> empExprList = new ArrayList<EmpExpr>();
        EmpExpr empExpr1 = new EmpExpr(null, 1, LocalDate.parse("2011-07-09"), LocalDate.parse("2017-04-06"), "A公司", "行銷專員");
        EmpExpr empExpr2 = new EmpExpr(null, 1, LocalDate.parse("2017-06-26"), LocalDate.parse("2023-05-08"), "B公司", "行銷專員");
        Collections.addAll(empExprList, empExpr1, empExpr2);
        emp.setExprList(empExprList);

        empService.update(emp);

        verify(empMapper, times(1)).updateById(emp);
        verify(empExprMapper, times(1)).deleteByEmpIds(List.of(1));
        verify(empExprMapper, times(1)).insertBatch(emp.getExprList());
    }

    @Test
    public void testLogin() {
        Emp empParam = new Emp();
        empParam.setUsername("Andy");
        empParam.setPassword("123456");

        Emp empReturn = new Emp();
        empReturn.setId(1);
        empReturn.setUsername("Andy");
        empReturn.setName("趙曉華");
        Mockito.when(empMapper.selectByUsernameAndPassword(any())).thenReturn(empReturn);

        LoginInfo result = empService.login(empParam);

        verify(empMapper, times(1)).selectByUsernameAndPassword(empParam);
        assertNotNull(result);
        assertNotNull(result.getToken());
        assertEquals(1, result.getId());
        assertEquals("Andy", result.getUsername());
        assertEquals("趙曉華", result.getName());
    }
}