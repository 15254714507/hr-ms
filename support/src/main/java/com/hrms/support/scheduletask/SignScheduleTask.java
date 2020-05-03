package com.hrms.support.scheduletask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 考勤表的定时任务
 *
 * @author 孔超
 * @date 2020/5/3 17:21
 */
@Slf4j
@Configuration
@EnableScheduling
public class SignScheduleTask {
    /**
     * 考勤表的定时任务，每天晚上11点半执行，把当天的考勤整理完
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 30 23 * * ?")
    public void signScheduleTaskLogin() {

    }
}
