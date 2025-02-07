package com.xxxxx.mapper;

import com.xxxxx.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//員工工作經歷
@Mapper
public interface EmpExprMapper {

    //批次新增員工的工作經歷資料(1筆或多筆)
    void insertBatch(List<EmpExpr> exprList);

    //根據員工id批次刪除員工的工作經歷(1筆或多筆)
    void deleteByEmpIds(List<Integer> empIds);

}
