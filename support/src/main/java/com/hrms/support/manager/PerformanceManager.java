package com.hrms.support.manager;

import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/30 2:19
 */
public interface PerformanceManager {
    /**
     * 根据id获得绩效信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Performance getById(Long id) throws DaoException;

    /**
     * 添加新的绩效信息
     *
     * @param performance
     * @return
     * @throws DaoException
     */
    public Long insert(Performance performance) throws DaoException;

    /**
     * 修改绩效信息
     *
     * @param performance
     * @return
     * @throws DaoException
     */
    public Long updateById(Performance performance) throws DaoException;

    /**
     * 删除绩效信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得绩效信息的集合
     *
     * @param performanceCondition
     * @return
     * @throws DaoException
     */
    public List<Performance> list(PerformanceCondition performanceCondition) throws DaoException;

    /**
     * 指定哪年哪月的绩效
     *
     * @param performanceCondition
     * @return
     * @throws DaoException
     */
    public List<Performance> listByYearMonth(PerformanceCondition performanceCondition) throws DaoException;
}
