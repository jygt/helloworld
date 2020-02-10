package com.example.helloWorld.dao;

import com.example.helloWorld.TUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// MyBatis根据接口定义与Mapper文件中的SQL语句动态创建接口实现
@Mapper
public interface TUrlMybatisDao {
    // Param将参数与Mapper文件中的#{}进行对应。
    TUrl findByName(@Param("name")String name);
}
