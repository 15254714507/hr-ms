package com.hrms.api.service;

import com.hrms.api.domain.dto.Employees;

/**
 * @author 孔超
 * @date 2020/4/13 23:06
 */
public interface UserJobService {
    /**
     * 获得职员部门岗位信息
     *
     * @param userId
     * @return
     */
    public Employees getEmployees(Long userId);
}
