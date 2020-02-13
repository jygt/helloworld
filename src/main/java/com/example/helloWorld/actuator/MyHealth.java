package com.example.helloWorld.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.io.File;

@Component("myHealth")   //myHealth 是你健康监测的名称
public class MyHealth extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        File[] rootFiles = File.listRoots();
        if (rootFiles != null && rootFiles.length != 0) {
            long total = 0, free = 0;
            for (File file : rootFiles) {
                total += file.getTotalSpace(); // 总量
                free += file.getUsableSpace(); // 未用
            }
            long user = total - free; // 已用
            double userRate = total == 0 ? 0 : ((double) user / total);// 利用率
            builder.up()
                    .withDetail("diskspaceTotal", total)   //这里是你要显示的具体健康监测信息
                    .withDetail("diskspaceFree", free)
                    .withDetail("diskspaceUsage", userRate * 100)
                    .build();
        } else {
            builder.down().build();
        }
    }
}
