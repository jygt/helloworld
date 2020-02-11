package com.example.helloWorld.security;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionRepository objRolePermissionRepository;

    @Override
    public List<RolePermission> findAll() {
        return objRolePermissionRepository.findAll();
    }

    @Override
    public List<RolePermission> findByPermissionId(String permissionId) {
        return objRolePermissionRepository.findByPermissionId(permissionId);
    }
}
