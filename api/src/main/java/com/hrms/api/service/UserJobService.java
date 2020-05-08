package com.hrms.api.service;

import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:06
 */
public interface UserJobService {
    /**
     * 根据id获得用户和岗位的对应关系
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public UserJob getById(Long id) throws DaoException;
    /**
     * 添加新用户和岗位的对应关系
     *
     * @param userJob
     * @return
     * @throws DaoException
     */
    public Long insert(UserJob userJob) throws DaoException;

    /**
     * 获得职员部门岗位信息
     *
     * @param userId
     * @return
     */
    public Employees getEmployees(Long userId);
    /**
     * 获得岗位信息的集合
     *
     * @param userJobCondition
     * @return
     * @throws DaoException
     */
    public List<UserJob> list(UserJobCondition userJobCondition) throws DaoException;
}
