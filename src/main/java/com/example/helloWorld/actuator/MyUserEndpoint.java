package com.example.helloWorld.actuator;

import com.example.helloWorld.security.UserRoleService;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component

@Endpoint(id="userInfo")
public class MyUserEndpoint {


    private Map<String, Object> features = new ConcurrentHashMap<>();

    @Resource
    private UserRoleService objUserRoleService;

    @ReadOperation
    public Map<String, Object> features() {
        features.put("count",(objUserRoleService.Count()));
        return features;
    }

    @ReadOperation
    public Object feature(@Selector String name) {
        return features.get(name);
    }

    @WriteOperation
    public void configureFeature(@Selector String name, Object feature) {
        features.put(name, feature);
    }

    @DeleteOperation
    public void deleteFeature(@Selector String name) {
        features.remove(name);
    }



}
