package com.hrms.api.service;

import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.dto.EmployeesWages;
import com.hrms.api.domain.entity.User;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/17 23:17
 */
public interface EmployeesService {
    /**
     * 职工的登录服务
     *
     * @param user
     * @return
     */
    public Employees login(User user);

    /**
     * 职员集合
     *
     * @return
     */
    public List<Employees> listEmployees();

    /**
     * 获得职员岗位薪酬对应关系
     *
     * @return
     */
    public List<EmployeesWages> listEmployeesWages();
}
