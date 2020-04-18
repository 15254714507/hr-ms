package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.UserJob;
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
    public Employees getEmployees(Long userId) {
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setUserId(userId);
        List<UserJob> userJobList = userJobManager.list(userJobCondition);
        if (userJobList == null || userJobList.size() < 1) {
            return null;
        }
        return jobService.getEmployees(userJobList.get(0).getJobId());
    }
}
