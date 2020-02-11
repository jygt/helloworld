package com.example.helloWorld.security;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission,String> {

    List<RolePermission> findByPermissionId(@Param("permissionId") String permissionId);
}
