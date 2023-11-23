package com.tony.springboot.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestTask {
    @Scheduled(cron = "0 1 * * * ?") //定时任务注解+cron表达式
    public void testScheduleTask() {
        System.out.println("执行定时任务" + LocalDateTime.now());
    }
}
