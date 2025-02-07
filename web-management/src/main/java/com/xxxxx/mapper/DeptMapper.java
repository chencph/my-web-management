package com.xxxxx.mapper;

import com.xxxxx.pojo.Dept;
import org.apache.ibatis.annotations.*;
import java.util.List;

//部門
@Mapper
public interface DeptMapper {

    //查詢所有部門的資料
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    public List<Dept> findAll();

    //根據id刪除部門資料
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部門資料
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    //根據id查詢部門資料
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    //修改(更新)部門資料
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

}
