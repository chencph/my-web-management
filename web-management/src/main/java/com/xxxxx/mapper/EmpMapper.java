package com.xxxxx.mapper;

import com.xxxxx.pojo.Emp;
import com.xxxxx.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//員工資料
@Mapper
public interface EmpMapper {

    //條件查詢員工資料
    List<Emp> list(EmpQueryParam empQueryParam);

    //新增員工基本資料
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    //根據id批次刪除員工的基本資料(1筆或多筆)
    void deleteByIds(List<Integer> ids);

    //根據id查詢員工資料(包含基本資料與工作經歷)
    Emp getById(Integer id);

    //根據id修改員工基本資料
    void updateById(Emp emp);

    //統計員工職位人數
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    //統計員工性別人數
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    //根據username和password查詢員工資料
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);

}
