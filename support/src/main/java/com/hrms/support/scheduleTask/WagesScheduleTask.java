package com.hrms.support.scheduleTask;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.domain.entity.User;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.domain.entity.Wages;
import com.hrms.api.service.PerformanceService;
import com.hrms.api.service.UserJobService;
import com.hrms.api.service.UserService;
import com.hrms.api.service.WagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 工资的定时任务，每个月的1号就会生成上个月的工资信息(不全，基本工资，绩效工资可以生成)
 *
 * @author 孔超
 * @date 2020/5/7 0:37
 */
@Slf4j
@Component
@EnableScheduling
public class WagesScheduleTask {
    private static final String CREATE_USER = "System";

    @Resource
    UserService userService;
    @Resource
    UserJobService userJobService;
    @Resource
    PerformanceService performanceService;
    @Resource
    WagesService wagesService;

    /**
     * 每个月一号生成上个月的工资信息
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void wagesScheduleTaskLogin() {
        LocalDate date = LocalDate.now();
        date = date.minusMonths(1);
        UserCondition userCondition = new UserCondition();
        List<User> userList = userService.list(userCondition);
        for (User user : userList) {
            initWages(user, date);
        }
    }

    /**
     * 初始化工资信息
     *
     * @param user
     * @param date 上个月的时间
     */
    private void initWages(User user, LocalDate date) {
        Wages wages = new Wages();
        wages.setUsername(user.getUsername());
        wages.setName(user.getName());
        wages.setWagesDate(date);
        //这个userJob有问题
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setUserId(user.getId());
        List<UserJob> userJobList = userJobService.list(userJobCondition);
        Performance performance = getPerformance(user.getId(), date);
        //说明上个月没有此人的绩效，是异常的
        if (performance == null || !performance.getStatus()) {
            wages.setStatus(2);
        } else {
            UserJob userJob = userJobList.get(0);
            //说明上个月有此人的绩效，就可以计算他绩效工资和基础工资
            wages.setBaseSalary(userJob.getBaseSalary().doubleValue());
            wages.setPerformanceSalary(userJob.getPerformanceSalary() * performance.getKpi());
            wages.setStatus(0);
        }
        insertWages(wages);
    }

    /**
     * 获得用户指定时间的绩效信息
     *
     * @param userId
     * @param date
     * @return
     */
    private Performance getPerformance(Long userId, LocalDate date) {
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setUserId(userId);
        performanceCondition.setYear(date.getYear());
        performanceCondition.setMonth(date.getMonthValue());
        performanceCondition.setStatus(true);
        List<Performance> performanceList = performanceService.list(performanceCondition);
        if (performanceList == null || performanceList.size() < 1) {
            return null;
        }
        return performanceList.get(0);
    }

    /**
     * 添加新的工资信息
     *
     * @param wages
     */
    @Transactional(rollbackFor = Exception.class)
    void insertWages(Wages wages) {
        wages.setCreateUser(CREATE_USER);
        wages.setCreateUser(CREATE_USER);
        try {
            Long isSuc = wagesService.insert(wages);
            if (isSuc != 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                log.error("创建当月的工资信息时发现已存在wages{}", JSON.toJSONString(wages));

            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("初始化工资信息时出现系统异常wages{}", JSON.toJSONString(wages), e);
        }
    }
}
