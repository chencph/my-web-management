package com.xxxxx.mapper;

import com.xxxxx.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeptMapperTest {

    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void testFindAll() {
        List<Dept> deptList = deptMapper.findAll();
        assertNotNull(deptList);
        assertFalse(deptList.isEmpty());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        deptMapper.deleteById(1);

        Dept dept = deptMapper.getById(1);
        assertNull(dept);
    }

    @Test
    @Transactional
    public void testInsert() {
        int initialSize = deptMapper.findAll().size();

        Dept dept = new Dept();
        dept.setName("設計部");
        deptMapper.insert(dept);

        List<Dept> deptList = deptMapper.findAll();
        assertEquals(initialSize + 1, deptList.size());
        assertTrue(deptList.stream().anyMatch(d -> d.getName().equals("產品部")));
    }

    @Test
    public void testGetById() {
        Dept dept = deptMapper.getById(1);
        assertNotNull(dept);
        assertEquals("行政部", dept.getName());
        assertNotNull(dept.getCreateTime());
        assertNotNull(dept.getUpdateTime());
    }

    @Test
    @Transactional
    public void testUpdate() {
        Dept dept = deptMapper.getById(1);
        dept.setName("採購部");

        deptMapper.update(dept);

        Dept result = deptMapper.getById(1);
        assertNotNull(result);
        assertEquals("採購部", result.getName());
    }

}