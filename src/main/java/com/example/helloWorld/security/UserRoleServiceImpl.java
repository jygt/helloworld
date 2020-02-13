package com.example.helloWorld.security;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleRepository objUserRoleRepository;

    @Override
    public Long Count() {
        return objUserRoleRepository.count();
    }

    @Override
    public List<UserRole> findByUserId(String userId){
        return objUserRoleRepository.findByUserId(userId);
    }
}
