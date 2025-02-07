package com.xxxxx.mapper;

import com.xxxxx.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

//將增,刪,改相關操作日誌記錄到資料庫表中
@Mapper
public interface OperateLogMapper {

    //插入日誌資料
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) "+
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    public void insert(OperateLog log);

}
