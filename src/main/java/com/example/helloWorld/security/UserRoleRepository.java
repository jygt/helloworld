package com.example.helloWorld.security;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    // 自定义的方法，获取指定id的数据
    List<UserRole> findByUserId(@Param("userId")String userId);
}
