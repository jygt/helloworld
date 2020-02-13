package com.example.helloWorld.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("myHealth2")//显示的名字
class MyHealth2 implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = new Random().nextInt(5); // 定义一个错误代码 随机产生
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();  //down就是不健康的状态
        } else {
            return Health.up().withDetail("Ok Code", errorCode).build();  //ok就是健康的状态
        }
    }
}