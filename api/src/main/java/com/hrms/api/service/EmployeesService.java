package com.hrms.api.service;

import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.User;

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
}
