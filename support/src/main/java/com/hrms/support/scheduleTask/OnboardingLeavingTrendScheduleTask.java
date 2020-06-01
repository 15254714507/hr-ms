package com.hrms.support.scheduleTask;

import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.domain.entity.OnboardingLeavingTrend;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.DimissionUserService;
import com.hrms.api.service.OnboardingLeavingTrendService;
import com.hrms.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 每个月的月底晚上计算每个月的离职人数和入职人数
 *
 * @author 孔超
 * @date 2020/6/2 1:35
 */
@Slf4j
@Configuration
@EnableScheduling
public class OnboardingLeavingTrendScheduleTask {
    private static final String CREATE_USER = "System";
    @Resource
    DimissionUserService dimissionUserService;
    @Resource
    UserService userService;
    @Resource
    OnboardingLeavingTrendService onboardingLeavingTrendService;

    /**
     * 计算每个月的入职人数和离职人数
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 30 23 L * ?")
    public void performanceScheduleTaskLogin() {
        LocalDate monthEarly = LocalDate.now().withDayOfMonth(1);
        OnboardingLeavingTrend onboardingLeavingTrend = new OnboardingLeavingTrend();
        onboardingLeavingTrend.setMonth(monthEarly.getMonthValue());
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        //修改当前对象在当月的日期
        dimissionUserCondition.setStartDate(monthEarly);
        List<DimissionUser> dimissionUserList = dimissionUserService.list(dimissionUserCondition);
        if (dimissionUserList != null) {
            onboardingLeavingTrend.setNumberOfSeparations(dimissionUserList.size());
        } else {
            onboardingLeavingTrend.setNumberOfSeparations(0);
        }
        UserCondition userCondition = new UserCondition();
        userCondition.setStartDate(monthEarly);
        List<User> userList = userService.list(userCondition);
        if (userList != null) {
            onboardingLeavingTrend.setNumberOfEmployees(userList.size());
        } else {
            onboardingLeavingTrend.setNumberOfEmployees(0);
        }
        onboardingLeavingTrend.setCreateUser(CREATE_USER);
        onboardingLeavingTrend.setUpdateUser(CREATE_USER);
        onboardingLeavingTrendService.insert(onboardingLeavingTrend);
    }
}
