package com.xxxxx.mapper;

import com.xxxxx.pojo.EmpExpr;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmpExprMapperTest {

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    public void testInsertBatch() {
        List<EmpExpr> empExprList = Arrays.asList(
            new EmpExpr(null, 3,  LocalDate.parse("2015-03-22"),  LocalDate.parse("2018-06-07"), "A公司", "行銷專員"),
            new EmpExpr(null, 3,  LocalDate.parse("2018-08-27"),  LocalDate.parse("2024-11-16"), "B公司", "行銷主管")
        );

        empExprMapper.insertBatch(empExprList);

        Integer count = jdbcTemplate.queryForObject("select count(*) from emp_expr where emp_id = 3", Integer.class);
        assertEquals(2, count);
    }

    @Test
    @Transactional
    public void testDeleteByEmpIds() {
        jdbcTemplate.update("insert into emp_expr (emp_id, `begin`, `end`, company, job) values (3, '2016-03-03', '2023-10-09', 'C公司', '會計師')");
        jdbcTemplate.update("insert into emp_expr (emp_id, `begin`, `end`, company, job) values (4, '2020-11-22', '2024-05-28', 'D公司', '產品研發人員')");
        jdbcTemplate.update("insert into emp_expr (emp_id, `begin`, `end`, company, job) values (5, '2018-07-09', '2025-01-12', 'E公司', '業務經理')");

        List<Integer> ids = new ArrayList<>();
        Collections.addAll(ids, 3, 4, 5);
        empExprMapper.deleteByEmpIds(ids);

        Integer count = jdbcTemplate.queryForObject("select count(*) from emp_expr where emp_id in (3, 4, 5)", Integer.class);
        assertEquals(0, count);
    }

}