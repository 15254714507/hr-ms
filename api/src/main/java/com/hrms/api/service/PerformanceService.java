package com.hrms.api.service;

import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.dto.UserPerformance;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/30 21:08
 */
public interface PerformanceService {
    /**
     * 修改绩效信息
     *
     * @param performance
     * @return
     * @throws DaoException
     */
    public Long updateById(Performance performance) throws DaoException;

    /**
     * 添加新的绩效信息
     *
     * @param performance
     * @return
     * @throws DaoException
     */
    public Long insert(Performance performance) throws DaoException;

    /**
     * 获得用户绩效信息
     *
     * @param performanceCondition
     * @return
     */
    public List<UserPerformance> listUserPerformance(PerformanceCondition performanceCondition);
}
