package com.hrms.api.service;

import com.hrms.api.domain.dto.Employees;

/**
 * @author 孔超
 * @date 2020/4/13 23:07
 */
public interface JobService {
    /**
     * 获得职员岗位的信息
     *
     * @param jobId 根据岗位的id
     * @return
     */
    public Employees getEmployees(Long jobId);
}
