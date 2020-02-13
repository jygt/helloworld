package com.example.helloWorld.actuator;

import com.sun.istack.NotNull;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MyMetrics  implements MeterBinder {
    private File rootFilePath;

    public MyMetrics() {
        this.rootFilePath = new File(".");
    }

    @Override
    public void bindTo(@NotNull MeterRegistry registry) {
        // 磁盘已用容量
        Gauge.builder("磁盘已用容量", rootFilePath, File::getTotalSpace)
                .register(registry);
        // 磁盘剩余容量
        Gauge.builder("磁盘剩余容量", rootFilePath, File::getFreeSpace)
                .register(registry);
        // 磁盘使用率
        Gauge.builder("磁盘使用率", rootFilePath, c -> {
            long totalDiskSpace = rootFilePath.getTotalSpace();
            if (totalDiskSpace == 0) {
                return 0.0;
            }
            long usedDiskSpace = totalDiskSpace - rootFilePath.getFreeSpace();
            return (double) usedDiskSpace / totalDiskSpace * 100;
        }).register(registry);
    }
}
