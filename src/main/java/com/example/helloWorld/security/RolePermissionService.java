package com.example.helloWorld.security;

import java.util.List;

public interface RolePermissionService {
    List<RolePermission> findAll();
    List<RolePermission> findByPermissionId(String permissionId);
}
