package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.DepartmentCondition;
import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Department;
import com.hrms.api.domain.entity.Job;
import com.hrms.api.service.DepartmentService;
import com.hrms.api.service.JobService;
import com.hrms.support.manager.JobManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        employees.setLead(job.getLead());
        return employees;
    }

    @Override
    public List<DepartmentJob> listAllDepartment() {
        DepartmentCondition departmentCondition = new DepartmentCondition();
        List<Department> departmentList = departmentService.list(departmentCondition);
        ArrayList<DepartmentJob> departmentJobArrayList = new ArrayList<>();
        for (Department department : departmentList) {
            departmentJobArrayList.add(getDepartmentJob(department));
        }
        return departmentJobArrayList;
    }

    /**
     * 根据部门的id返回此岗位下的所有岗位
     *
     * @param department
     * @return
     */
    private DepartmentJob getDepartmentJob(Department department) {
        DepartmentJob departmentJob = new DepartmentJob();
        departmentJob.setDepartmentId(department.getId());
        departmentJob.setDepartmentName(department.getDepartmentName());
        JobCondition jobCondition = new JobCondition();
        jobCondition.setDepartmentId(department.getId());
        List<Job> jobList = jobManager.list(jobCondition);
        departmentJob.setJobList(jobList);
        return departmentJob;
    }
}
