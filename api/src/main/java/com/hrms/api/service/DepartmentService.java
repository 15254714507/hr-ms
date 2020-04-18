package com.hrms.api.service;

import com.hrms.api.domain.dto.Employees;

/**
 * @author 孔超
 * @date 2020/4/13 23:07
 */
public interface DepartmentService {
    /**
     * 获得职员的部门信息
     *
     * @param departmentId
     * @return
     */
    public Employees getEmployees(Long departmentId);
}
