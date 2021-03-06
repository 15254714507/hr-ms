package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.dto.EmployeesWages;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.EmployeesService;
import com.hrms.api.service.UserJobService;
import com.hrms.api.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/17 23:31
 */
@Slf4j
@Service(interfaceClass = EmployeesService.class)
public class EmployeesServiceImpl implements EmployeesService {
    @Resource
    UserService userService;
    @Resource
    UserJobService userJobService;

    @Override
    public Employees login(User user) {
        UserCondition userCondition = new UserCondition();
        userCondition.setUsername(user.getUsername());
        userCondition.setPassword(user.getPassword());
        user = userService.getUserByUserNamePassword(userCondition);
        if (user == null) {
            return null;
        }
        return getEmployees(user);
    }

    /**
     * 获得职员
     *
     * @param user
     * @return
     */
    private Employees getEmployees(User user) {
        Employees employees = userJobService.getEmployees(user.getId());
        if (employees == null) {
            return null;
        }
        employees.setUserId(user.getId());
        employees.setUsername(user.getUsername());
        employees.setName(user.getName());
        employees.setHeadShot(user.getHeadShot());
        return employees;
    }

    @Override
    public List<Employees> listEmployees() {
        List<Employees> employeesList = new ArrayList<>();
        UserCondition userCondition = new UserCondition();
        List<User> userList = userService.list(userCondition);
        for (User user : userList) {
            employeesList.add(getEmployees(user));
        }
        return employeesList;
    }

    @Override
    public List<EmployeesWages> listEmployeesWages() {
        List<EmployeesWages> employeesWagesList = new ArrayList<>();
        UserCondition userCondition = new UserCondition();
        List<User> userList = userService.list(userCondition);
        for (User user : userList) {
            employeesWagesList.add(getEmployeesWages(user));
        }
        return employeesWagesList;
    }

    /**
     * 获得职员的岗位薪资对应
     *
     * @param user
     * @return
     */
    private EmployeesWages getEmployeesWages(User user) {
        EmployeesWages employeesWages = userJobService.getEmployeesWages(user.getId());
        if (employeesWages == null) {
            return null;
        }
        employeesWages.getEmployees().setUserId(user.getId());
        employeesWages.getEmployees().setUsername(user.getUsername());
        employeesWages.getEmployees().setName(user.getName());
        return employeesWages;
    }
}
