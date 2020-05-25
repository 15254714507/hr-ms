package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.DepartmentCondition;
import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.entity.*;
import com.hrms.api.exception.DaoException;
import com.hrms.api.exception.ServiceException;
import com.hrms.api.service.*;
import com.hrms.api.until.Result;
import com.hrms.support.manager.RegisterNewEmployeeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Resource
    UserService userService;
    @Resource
    UserJobService userJobService;


    @Override
    public RegisterNewEmployee getById(Long id) throws DaoException {
        return registerNewEmployeeManager.getById(id);
    }

    @Override
    public Result insert(RegisterNewEmployee registerNewEmployee) {
        Result result = fillRegisterNewEmployee(registerNewEmployee);
        if (result != null) {
            return result;
        }
        Long isSuc = registerNewEmployeeManager.insert(registerNewEmployee);
        if (isSuc.intValue() == 0) {
            return new Result(0, "此职工编码已存在");
        }
        return new Result(1, "添加成功");
    }

    @Override
    public Result updateById(RegisterNewEmployee registerNewEmployee) throws DaoException {
        Result result = null;
        //说明修改内容包括部门和岗位
        if (registerNewEmployee.getDepartmentId() != null) {
            result = fillRegisterNewEmployee(registerNewEmployee);
            if (result != null) {
                return result;
            }
        }
        Long isSuc = registerNewEmployeeManager.updateById(registerNewEmployee);
        if (isSuc == 0) {
            result = new Result(0, "此待审核职员不存在");
        }
        return result;
    }

    @Override
    public Long deleteById(Long id) throws DaoException {
        return registerNewEmployeeManager.deleteById(id);
    }

    @Override
    public List<RegisterNewEmployee> list(RegisterNewEmployeeCondition registerNewEmployeeCondition) throws DaoException {
        return registerNewEmployeeManager.list(registerNewEmployeeCondition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result pass(RegisterNewEmployee registerNewEmployee) throws ServiceException, DaoException {
        Long isSuc = registerNewEmployeeManager.updateById(registerNewEmployee);
        if (isSuc == 0) {
            log.warn("审核通过的新职员不存在registerNewEmployee {}", JSON.toJSONString(registerNewEmployee));
            return new Result(0, "审核通过新职员不存在，请刷新后重试");
        }
        Result result = null;
        try {
            result = insertUser(registerNewEmployee.getId());
            if (result == null) {
                result = new Result(1, "审核成功,新员工已生效");
            } else {
                //如果有错，这个事务必须要回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            log.error("审核新职员通过后往user表和userJob表添加数据时发生异常 registerNewEmployee{}", JSON.toJSONString(registerNewEmployee), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertImportExcelList(List<RegisterNewEmployee> registerNewEmployeeList) throws DaoException {
        for (RegisterNewEmployee registerNewEmployee : registerNewEmployeeList) {
            Result result = insertImportExcel(registerNewEmployee);
            if (result != null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        return new Result(1, "导入新员工信息成功");
    }

    /**
     * 插入导入的excel中的新员工的信息
     *
     * @param registerNewEmployee
     * @return 如果为空表示没问题，如果不为空说明出现问题了
     */
    private Result insertImportExcel(RegisterNewEmployee registerNewEmployee) {
        DepartmentCondition departmentCondition = new DepartmentCondition();
        departmentCondition.setDepartmentName(registerNewEmployee.getDepartmentName());
        List<Department> departmentList = departmentService.list(departmentCondition);
        if (departmentList == null || departmentList.size() < 1) {
            return new Result(0, "没有" + registerNewEmployee.getName() + "的所属的部门名称：" + registerNewEmployee.getDepartmentName());
        }
        JobCondition jobCondition = new JobCondition();
        jobCondition.setDepartmentId(departmentList.get(0).getId());
        jobCondition.setJobName(registerNewEmployee.getJobName());
        List<Job> jobList = jobService.list(jobCondition);
        if (jobList == null || jobList.size() < 1) {
            return new Result(0, registerNewEmployee.getName() + "的所属的部门没有此岗位：" + registerNewEmployee.getJobName());
        }
        registerNewEmployee.setDepartmentId(departmentList.get(0).getId());
        registerNewEmployee.setJobId(jobList.get(0).getId());
        for (int i = 1; i <= registerNewEmployee.getPhone().length(); i++) {
            //员工账号默认是姓名拼音加手机号的后几位，最后一位，最后两位逐渐增加
            String phone = registerNewEmployee.getPhone();
            registerNewEmployee.setUsername(registerNewEmployee.getUsername() + phone.substring(phone.length() - i));
            Result result = insert(registerNewEmployee);
            if (result.getCode() == 1) {
                return null;
            }
        }
        //一般不会出现这种情况，每个人的手机号肯定是不一样的
        return new Result(0, registerNewEmployee.getName() + "的姓名拼音和手机号码都已经存在");
    }

    /**
     * 插入user表
     *
     * @param registerNewEmployeeId
     * @return
     */
    private Result insertUser(Long registerNewEmployeeId) {
        RegisterNewEmployee registerNewEmployee = registerNewEmployeeManager.getById(registerNewEmployeeId);
        User user = fillUser(registerNewEmployee);
        Long isSuc = userService.insert(user);
        if (isSuc == 0) {
            return new Result(0, "新员工的员工编码已存在");
        }
        UserJob userJob = fillUserJob(registerNewEmployee);
        isSuc = userJobService.insert(userJob);
        if (isSuc == 0) {
            return new Result(0, "此员工对应的此岗位已存在，请勿重复操作");
        }
        return null;
    }

    /**
     * 填充User
     *
     * @param registerNewEmployee
     * @return
     */
    private User fillUser(RegisterNewEmployee registerNewEmployee) {
        User user = new User();
        user.setUsername(registerNewEmployee.getUsername());
        //初试密码
        user.setPassword("admin");
        user.setName(registerNewEmployee.getName());
        user.setAddress(registerNewEmployee.getAddress());
        user.setCensusRegister(registerNewEmployee.getCensusRegister());
        user.setDateOfBirth(registerNewEmployee.getDateOfBirth());
        user.setDegree(registerNewEmployee.getDegree());
        user.setPhone(registerNewEmployee.getPhone());
        user.setEmail(registerNewEmployee.getEmail());
        user.setEmploymentDate(registerNewEmployee.getEmploymentDate());
        user.setFirstWorkDate(registerNewEmployee.getFirstWorkDate());
        user.setGender(registerNewEmployee.getGender());
        user.setGraduationDate(registerNewEmployee.getGraduationDate());
        //默认头像
        user.setHeadShot("head_shot/0.jpg");
        user.setIdentityType(registerNewEmployee.getIdentityType());
        user.setIdentityCard(registerNewEmployee.getIdentityCard());
        user.setInternshipDate(registerNewEmployee.getInternshipDate());
        user.setNational(registerNewEmployee.getNational());
        user.setNationality(registerNewEmployee.getNationality());
        user.setNativePlace(registerNewEmployee.getNativePlace());
        user.setProfessional(registerNewEmployee.getProfessional());
        user.setUniversity(registerNewEmployee.getUniversity());
        user.setWorkYears(registerNewEmployee.getWorkYears());
        user.setCreateUser(registerNewEmployee.getCreateUser());
        user.setUpdateUser(registerNewEmployee.getUpdateUser());
        return user;
    }

    /**
     * 填充UserJob
     *
     * @param registerNewEmployee
     * @return
     */
    private UserJob fillUserJob(RegisterNewEmployee registerNewEmployee) {
        UserJob userJob = new UserJob();
        User user = userService.getByUsername(registerNewEmployee.getUsername());
        userJob.setUserId(user.getId());
        userJob.setJobId(registerNewEmployee.getJobId());
        userJob.setBaseSalary(registerNewEmployee.getBaseSalary());
        userJob.setPerformanceSalary(registerNewEmployee.getPerformanceSalary());
        userJob.setTypesOfEmployees(registerNewEmployee.getTypesOfEmployees());
        userJob.setCreateUser(registerNewEmployee.getCreateUser());
        userJob.setUpdateUser(registerNewEmployee.getUpdateUser());
        return userJob;
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
