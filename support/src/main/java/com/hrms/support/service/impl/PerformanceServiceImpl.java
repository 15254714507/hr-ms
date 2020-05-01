package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.dto.UserPerformance;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.domain.entity.User;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.PerformanceService;
import com.hrms.api.service.UserJobService;
import com.hrms.api.service.UserService;
import com.hrms.support.manager.PerformanceManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/30 21:08
 */
@Slf4j
@Service(interfaceClass = PerformanceService.class)
public class PerformanceServiceImpl implements PerformanceService {
    @Resource
    PerformanceManager performanceManager;
    @Resource
    UserService userService;
    @Resource
    UserJobService userJobService;

    @Override
    public Long updateById(Performance performance) throws DaoException {
        return performanceManager.updateById(performance);
    }

    @Override
    public Long insert(Performance performance) throws DaoException {
        return performanceManager.insert(performance);
    }

    @Override
    public List<UserPerformance> listUserPerformance(PerformanceCondition performanceCondition) {
        List<Performance> performanceList = performanceManager.listByYearMonth(performanceCondition);
        List<UserPerformance> userPerformanceList = new ArrayList<>();
        for (Performance performance : performanceList) {
            userPerformanceList.add(getUserPerformance(performance));
        }
        return userPerformanceList;
    }

    /**
     * 根据performance得到user再拼装成UserPerformance
     *
     * @param performance
     * @return
     */
    private UserPerformance getUserPerformance(Performance performance) {
        UserPerformance userPerformance = new UserPerformance();
        fillPerformance(userPerformance, performance);
        fillUser(userPerformance, performance.getId());
        fillUserJob(userPerformance, performance.getId());
        return userPerformance;
    }

    /**
     * 往userPerformance拼装performance的数据
     *
     * @param userPerformance
     * @param performance
     */
    private void fillPerformance(UserPerformance userPerformance, Performance performance) {
        userPerformance.setGoal(performance.getGoal());
        userPerformance.setKpi(performance.getKpi());
        userPerformance.setPerformanceId(performance.getId());
        userPerformance.setStatus(performance.getStatus());
        userPerformance.setAuditUser(performance.getAuditUser());
        String yearMonth = performance.getCreateTime().getYear() + "年" + performance.getCreateTime().getMonthValue() + "月";
        userPerformance.setYearMonth(yearMonth);
    }

    /**
     * 往userPerformance拼装 user的数据
     *
     * @param userPerformance
     * @param userId
     */
    private void fillUser(UserPerformance userPerformance, Long userId) {
        User user = userService.getById(userId);
        userPerformance.setUserId(user.getId());
        userPerformance.setName(user.getName());
    }

    /**
     * 往userPerformance拼装UserJob中的数据
     *
     * @param userPerformance
     * @param userId
     */
    private void fillUserJob(UserPerformance userPerformance, Long userId) {
        Employees employees = userJobService.getEmployees(userId);
        userPerformance.setDepartmentName(employees.getDepartmentName());
        userPerformance.setJobName(employees.getJobName());
    }
}
