package com.hrms.support.scheduletask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 每个月初都会产生当月的绩效
 *
 * @author 孔超
 * @date 2020/4/29 22:43
 */
@Slf4j
@Configuration
@EnableScheduling
public class PerformanceScheduleTask {
    /**
     * 库存异常的定时任务，每月1号凌晨1点执行一次
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void performanceScheduleTaskLogin() {

    }
}
