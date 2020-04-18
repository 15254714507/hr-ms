package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Job;
import com.hrms.api.service.DepartmentService;
import com.hrms.api.service.JobService;
import com.hrms.support.manager.JobManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author 孔超
 * @date 2020/4/13 23:10
 */
@Slf4j
@Service(interfaceClass = JobService.class)
public class JobServiceImpl implements JobService {
    @Resource
    DepartmentService departmentService;
    @Resource
    JobManager jobManager;

    @Override
    public Employees getEmployees(Long jobId) {
        Job job = jobManager.getById(jobId);
        if (job == null) {
            return null;
        }
        Employees employees = departmentService.getEmployees(job.getDepartmentId());
        employees.setJobName(job.getJobName());
        return employees;
    }
}
