package com.xxxxx.mapper;

import com.xxxxx.pojo.OperateLog;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OperateLogMapperTest {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    public void testInsert() {
        OperateLog log = new OperateLog();
        log.setOperateEmpId(1);
        log.setOperateTime(LocalDateTime.parse("2025-02-05T20:00:50.896513700"));
        log.setClassName("com.xxxxx.controller.DeptController");
        log.setMethodName("add");
        log.setMethodParams("[Dept(id=null, name=法務部, createTime=2025-02-05T20:00:50.896513700, updateTime=2025-02-05T20:00:50.896513700)]");
        log.setReturnValue("Result(code=1, msg=success, data=null)");
        log.setCostTime(375L);
        operateLogMapper.insert(log);

        Integer count = jdbcTemplate.queryForObject("select count(*) from operate_log where operate_emp_id = 1", Integer.class);
        assertEquals(1, count);
    }

}