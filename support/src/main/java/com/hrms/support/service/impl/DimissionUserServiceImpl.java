package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.domain.entity.User;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.service.DimissionUserService;
import com.hrms.api.service.UserJobService;
import com.hrms.api.service.UserService;
import com.hrms.api.until.Result;
import com.hrms.support.manager.DimissionUserManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/27 18:40
 */
@Slf4j
@Service(interfaceClass = DimissionUserService.class)
public class DimissionUserServiceImpl implements DimissionUserService {
    @Resource
    DimissionUserManager dimissionUserManager;
    @Resource
    UserService userService;
    @Resource
    UserJobService userJobService;

    @Override
    public DimissionUser getDimissionUserMsgByUsername(String username) {
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setUsername(username);
        List<DimissionUser> dimissionUserList = dimissionUserManager.list(dimissionUserCondition);
        if (dimissionUserList == null || dimissionUserList.size() < 1) {
            return assemblyDimissionUser(username);
        }
        return dimissionUserList.get(0);
    }

    @Override
    public Result insert(DimissionUser dimissionUser) {
        Result result = addTypesOfEmployees(dimissionUser);
        if (result != null) {
            return result;
        }
        Long isSuc = dimissionUserManager.insert(dimissionUser);
        if (isSuc != 1) {
            return new Result(0, "已申请，请勿重复申请离职");
        }
        return new Result(1, "申请成功");
    }

    private Result addTypesOfEmployees(DimissionUser dimissionUser) {
        User user = userService.getByUsername(dimissionUser.getUsername());
        if (user == null) {
            return new Result(0, "没有此用户,请刷新后重试");
        }
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setUserId(user.getId());
        List<UserJob> userJobList = userJobService.list(userJobCondition);
        if (userJobList == null || userJobList.size() < 1) {
            return new Result(0, "没有此用户的岗位，请联系运营人员");
        }
        dimissionUser.setTypesOfEmployees(userJobList.get(0).getTypesOfEmployees());
        return null;
    }

    /**
     * 在离职表里没有查到此账号的信息说明此账号还没有申请离职，所以拼装一个离职信息返回
     *
     * @param username
     * @return
     */
    private DimissionUser assemblyDimissionUser(String username) {
        User user = userService.getByUsername(username);
        //说明没有此账号，就返回空
        if (user == null) {
            return null;
        }
        Employees employees = userJobService.getEmployees(user.getId());
        DimissionUser dimissionUser = transEmployeesToDimissionUser(employees);
        dimissionUser.setUsername(user.getUsername());
        dimissionUser.setName(user.getName());
        dimissionUser.setDateOfEntry(user.getEmploymentDate());
        return dimissionUser;
    }

    /**
     * employees转换成DimissionUser
     *
     * @param employees
     * @return
     */
    private DimissionUser transEmployeesToDimissionUser(Employees employees) {
        DimissionUser dimissionUser = new DimissionUser();
        dimissionUser.setDepartmentName(employees.getDepartmentName());
        dimissionUser.setJobName(employees.getJobName());
        //离职流程第一步申请为0
        dimissionUser.setSteps(0);
        return dimissionUser;
    }


}
