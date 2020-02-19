package com.example.helloWorld.mongo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class UserDocServiceImpl implements UserDocService {

    @Resource
    private UserDocRepository objUserDocRepository;

    @Override
    public UserDoc save(UserDoc objUserDoc) {
        return objUserDocRepository.save(objUserDoc);
    }
}
