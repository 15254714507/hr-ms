package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.entity.Department;
import com.hrms.api.domain.entity.Job;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.DepartmentService;
import com.hrms.api.service.JobService;
import com.hrms.api.service.RegisterNewEmployeeService;
import com.hrms.api.until.Result;
import com.hrms.support.manager.RegisterNewEmployeeManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/21 19:26
 */
@Slf4j
@Service(interfaceClass = RegisterNewEmployeeService.class)
public class RegisterNewEmployeeServiceImpl implements RegisterNewEmployeeService {
    @Resource
    DepartmentService departmentService;
    @Resource
    JobService jobService;
    @Resource
    RegisterNewEmployeeManager registerNewEmployeeManager;

    @Override
    public Result insert(RegisterNewEmployee registerNewEmployee) {
        Result result = fillRegisterNewEmployee(registerNewEmployee);
        if (result != null) {
            return result;
        }
        Long isSuc = registerNewEmployeeManager.insert(registerNewEmployee);
        if (isSuc.intValue() == 0) {
            return new Result(0, "此职工编码也存在");
        }
        return new Result(1, "添加成功");
    }

    @Override
    public List<RegisterNewEmployee> list(RegisterNewEmployeeCondition registerNewEmployeeCondition) throws DaoException {
        return registerNewEmployeeManager.list(registerNewEmployeeCondition);
    }

    /**
     * 填充registerNewEmployee
     *
     * @param registerNewEmployee
     * @return 返回错误信息
     */
    private Result fillRegisterNewEmployee(RegisterNewEmployee registerNewEmployee) {
        Department department = departmentService.getById(registerNewEmployee.getDepartmentId());
        if (department == null) {
            return new Result(0, "部门不存在，请刷新后重试");
        }
        registerNewEmployee.setDepartmentName(department.getDepartmentName());
        JobCondition jobCondition = new JobCondition();
        jobCondition.setDepartmentId(department.getId());
        jobCondition.setJobName(registerNewEmployee.getJobName());
        List<Job> jobList = jobService.list(jobCondition);
        if (jobList == null || jobList.size() < 1) {
            return new Result(0, "岗位不存在,请刷新后重试");
        }
        registerNewEmployee.setJobId(jobList.get(0).getId());
        return null;
    }
}
