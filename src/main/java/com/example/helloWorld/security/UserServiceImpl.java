package com.example.helloWorld.security;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository objUserRepository;

    @Override
    public User findByName(String name){
        return objUserRepository.findByName(name);
    }
}
