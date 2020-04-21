package com.hrms.api.service;

import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Job;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:07
 */
public interface JobService {
    /**
     * 根据岗位的id获得岗位的信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Job getById(Long id) throws DaoException;

    /**
     * 根据条件搜索岗位对象的集合
     *
     * @param jobCondition
     * @return
     * @throws DaoException
     */
    public List<Job> list(JobCondition jobCondition) throws DaoException;

    /**
     * 获得职员岗位的信息
     *
     * @param jobId 根据岗位的id
     * @return
     */
    public Employees getEmployees(Long jobId);

    /**
     * 获得所有的部门岗位
     *
     * @return
     */
    public List<DepartmentJob> listAllDepartment();
}
