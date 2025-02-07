package com.xxxxx.mapper;

import com.xxxxx.pojo.Emp;
import com.xxxxx.pojo.EmpQueryParam;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmpMapperTest {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testList() {
        EmpQueryParam empQueryParam = new EmpQueryParam();
        empQueryParam.setName("黃");
        empQueryParam.setGender(1);
        empQueryParam.setBegin(LocalDate.parse("2020-01-01"));
        empQueryParam.setEnd(LocalDate.parse("2020-12-31"));
        List<Emp> empList = empMapper.list(empQueryParam);
        assertNotNull(empList);
        assertEquals(1, empList.size());
    }

    @Test
    @Transactional
    public void testInsert() {
        Emp emp = new Emp();
        emp.setName("羅慧娟");
        emp.setGender(2);
        emp.setUsername("Becky");
        emp.setPhone("0914273928");
        emp.setJob(3);
        emp.setSalary(50000);
        emp.setImage("01.jpg");
        emp.setEntryDate(LocalDate.parse("2018-05-05"));
        emp.setDeptId(3);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        EmpQueryParam empQueryParam = new EmpQueryParam();
        empQueryParam.setName("羅慧娟");
        List<Emp> list = empMapper.list(empQueryParam);
        assertNotNull(list);
        assertEquals(1, list.size());

        Emp result = list.get(0);
        assertNotNull(result.getId());
        assertEquals(emp.getId(), result.getId());
        assertEquals(emp.getName(), result.getName());
        assertEquals(emp.getGender(), result.getGender());
        assertEquals(emp.getUsername(), result.getUsername());
        assertEquals(emp.getPhone(), result.getPhone());
        assertEquals(emp.getJob(), result.getJob());
        assertEquals(emp.getSalary(), result.getSalary());
        assertEquals(emp.getImage(), result.getImage());
        assertEquals(emp.getEntryDate(), result.getEntryDate());
        assertEquals(emp.getDeptId(), result.getDeptId());
        assertNotNull(result.getCreateTime());
        assertNotNull(result.getUpdateTime());
    }

    @Test
    @Transactional
    public void testDeleteByIds() {
        List<Integer> ids = new ArrayList<>();
        Collections.addAll(ids, 1, 2, 3);
        empMapper.deleteByIds(ids);
        assertNull(empMapper.getById(1));
        assertNull(empMapper.getById(2));
        assertNull(empMapper.getById(3));
    }

    @Test
    public void testGetById() {
        Emp emp = empMapper.getById(1);
        assertNotNull(emp);
        assertEquals("Andy", emp.getUsername());
        assertEquals("e10adc3949ba59abbe56e057f20f883e", emp.getPassword());
        assertEquals("趙曉華", emp.getName());
        assertEquals(1, emp.getGender());
        assertEquals("0938274927", emp.getPhone());
        assertEquals(2, emp.getJob());
        assertEquals(45000, emp.getSalary());
        assertEquals("01.png", emp.getImage());
        assertNotNull(emp.getEntryDate());
        assertEquals(2, emp.getDeptId());
        assertNotNull(emp.getCreateTime());
        assertNotNull(emp.getUpdateTime());
    }

    @Test
    @Transactional
    public void testUpdateById() {
        Emp emp = empMapper.getById(1);
        emp.setName("王大明");
        empMapper.updateById(emp);
        Emp result = empMapper.getById(1);
        assertNotNull(result);
        assertEquals("王大明", result.getName());
    }

    @Test
    public void testCountEmpJobData() {
        List<Map<String, Object>> maps = empMapper.countEmpJobData();
        assertNotNull(maps);
    }

    @Test
    public void testCountEmpGenderData() {
        List<Map<String, Object>> maps = empMapper.countEmpGenderData();
        assertNotNull(maps);
    }

    @Test
    public void testSelectByUsernameAndPassword() {
        Emp emp = new Emp();
        emp.setUsername("Andy");
        emp.setPassword("e10adc3949ba59abbe56e057f20f883e");
        Emp result = empMapper.selectByUsernameAndPassword(emp);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Andy", result.getUsername());
        assertEquals("趙曉華", result.getName());
    }
}