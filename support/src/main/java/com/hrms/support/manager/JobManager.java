package com.hrms.support.manager;

import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.entity.Job;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:07
 */
public interface JobManager {
    /**
     * 根据id获得岗位信息
     *
     * @param id
     * @return 返回岗位对象
     * @throws DaoException
     */
    public Job getById(Long id) throws DaoException;

    /**
     * 添加新的岗位
     *
     * @param job
     * @return
     * @throws DaoException
     */
    public Long insert(Job job) throws DaoException;

    /**
     * 修改岗位信息
     *
     * @param job
     * @return
     * @throws DaoException
     */
    public Long updateById(Job job) throws DaoException;

    /**
     * 删除岗位信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得岗位信息的集合
     *
     * @param jobCondition
     * @return
     * @throws DaoException
     */
    public List<Job> list(JobCondition jobCondition) throws DaoException;
}
