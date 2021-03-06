package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.dto.EmployeesWages;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.JobService;
import com.hrms.api.service.UserJobService;
import com.hrms.support.manager.UserJobManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:12
 */
@Slf4j
@Service(interfaceClass = UserJobService.class)
public class UserJobServiceImpl implements UserJobService {
    @Resource
    UserJobManager userJobManager;
    @Resource
    JobService jobService;

    @Override
    public UserJob getById(Long id) throws DaoException {
        return userJobManager.getById(id);
    }

    @Override
    public Long insert(UserJob userJob) throws DaoException {
        return userJobManager.insert(userJob);
    }

    @Override
    public Long updateById(UserJob userJob) throws DaoException {
        return userJobManager.updateById(userJob);
    }

    @Override
    public Employees getEmployees(Long userId) {
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setUserId(userId);
        List<UserJob> userJobList = userJobManager.list(userJobCondition);
        if (userJobList == null || userJobList.size() < 1) {
            return null;
        }
        return jobService.getEmployees(userJobList.get(0).getJobId());
    }

    @Override
    public EmployeesWages getEmployeesWages(Long userId) {
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setUserId(userId);
        List<UserJob> userJobList = userJobManager.list(userJobCondition);
        if (userJobList == null || userJobList.size() < 1) {
            return null;
        }
        Employees employees = jobService.getEmployees(userJobList.get(0).getJobId());
        if (employees == null) {
            return null;
        }
        EmployeesWages employeesWages = new EmployeesWages();
        employeesWages.setEmployees(employees);
        employeesWages.setUserJobId(userJobList.get(0).getId());
        employeesWages.setBaseSalary(userJobList.get(0).getBaseSalary());
        employeesWages.setPerformanceSalary(userJobList.get(0).getPerformanceSalary());
        return employeesWages;
    }

    @Override
    public List<UserJob> list(UserJobCondition userJobCondition) throws DaoException {
        return userJobManager.list(userJobCondition);
    }
}
