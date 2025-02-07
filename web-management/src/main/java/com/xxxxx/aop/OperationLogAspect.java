package com.xxxxx.aop;

import com.xxxxx.mapper.OperateLogMapper;
import com.xxxxx.pojo.OperateLog;
import com.xxxxx.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

//將增,刪,改相關操作日誌記錄到資料庫表中
@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.xxxxx.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        //執行目標方法並計算耗時
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        //建構日誌
        OperateLog oLog = new OperateLog();
        oLog.setOperateEmpId(this.getCurrentUserId());
        oLog.setOperateTime(LocalDateTime.now());
        oLog.setClassName(joinPoint.getTarget().getClass().getName());
        oLog.setMethodName(joinPoint.getSignature().getName());
        oLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        oLog.setReturnValue(result != null ? result.toString() : "void");
        oLog.setCostTime(costTime);

        //保存日誌
        log.info("記錄操作日誌：{}", oLog);
        operateLogMapper.insert(oLog);

        return result;
    }

    //獲取當前登入員工的ID
    private Integer getCurrentUserId(){
        return CurrentHolder.getCurrentId();
    }

}
