package com.example.urlservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.urlapi.IndexService;

@Service(version = "2.6.2")
///@org.springframework.stereotype.Service

public class IndexServiceImpl implements IndexService {

    @Override
    public String index(String message) {
        System.out.println(message);
        return message+"-yes";
    }
}
