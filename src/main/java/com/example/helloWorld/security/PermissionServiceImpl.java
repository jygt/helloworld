package com.example.helloWorld.security;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service

public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionRepository objPermissionRepository;

    @Override
    public List<Permission> findAll() {
        return objPermissionRepository.findAll();
    }
}
