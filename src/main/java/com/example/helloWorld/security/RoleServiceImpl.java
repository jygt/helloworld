package com.example.helloWorld.security;

import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepository objRoleRepository;

    @Override
    public Role find(String id){
        return objRoleRepository.findById(id).get();
    }
}
