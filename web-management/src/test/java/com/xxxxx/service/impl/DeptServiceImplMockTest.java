package com.xxxxx.service.impl;

import com.xxxxx.mapper.DeptMapper;
import com.xxxxx.pojo.Dept;
import com.xxxxx.service.DeptService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DeptServiceImplMockTest {

    @MockitoBean
    private DeptMapper deptMapper;

    @Autowired
    private DeptService deptService;

    @Test
    public void testFindAll() {
        List<Dept> deptList = new ArrayList<>();
        deptList.add(new Dept(1, "行銷部", LocalDateTime.parse("2007-12-03T10:15:30"), LocalDateTime.parse("2025-02-05T10:15:31")));
        deptList.add(new Dept(2, "業務部", LocalDateTime.parse("2009-11-07T11:12:35"), LocalDateTime.parse("2025-02-05T09:35:36")));
        Mockito.when(deptMapper.findAll()).thenReturn(deptList);

        List<Dept> result = deptService.findAll();
        
        verify(deptMapper, times(1)).findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteById() {
        deptService.deleteById(1);
        
        verify(deptMapper, times(1)).deleteById(1);
    }

    @Test
    public void testAdd() {
        Dept dept = new Dept();
        dept.setName("行銷部");
        
        deptService.add(dept);
        
        verify(deptMapper, times(1)).insert(dept);
    }

    @Test
    public void testGetById() {
        Dept dept = new Dept(1, "行銷部", LocalDateTime.parse("2007-12-03T10:15:30"), LocalDateTime.parse("2025-02-05T10:15:31"));
        Mockito.when(deptMapper.getById(any())).thenReturn(dept);

        Dept result = deptService.getById(1);


        verify(deptMapper, times(1)).getById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("行銷部", result.getName());
        assertNotNull(result.getCreateTime());
        assertNotNull(result.getUpdateTime());
    }

    @Test
    public void testUpdate() {
        Dept dept = new Dept();
        dept.setId(1);
        dept.setName("財務部");

        deptService.update(dept);

        verify(deptMapper, times(1)).update(dept);
    }
}