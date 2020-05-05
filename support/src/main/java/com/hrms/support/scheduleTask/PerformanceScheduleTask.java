package com.hrms.support.scheduleTask;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.PerformanceService;
import com.hrms.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;

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

    private static final String CREATE_USER = "System";
    @Resource
    UserService userService;
    @Resource
    PerformanceService performanceService;

    /**
     * 绩效的地定时任务的开始，每月1号凌晨1点执行一次
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void performanceScheduleTaskLogin() {
        UserCondition userCondition = new UserCondition();
        List<User> userList = userService.list(userCondition);
        for (User user : userList) {
            Performance performance = new Performance();
            performance.setUserId(user.getId());
            performance.setCreateUser(CREATE_USER);
            performance.setUpdateUser(CREATE_USER);
            try {
                performanceService.insert(performance);
            } catch (Exception e) {
                log.error("创建user{}的当月绩效时出现系统异常", JSON.toJSONString(user), e);
            }
        }
    }

}
