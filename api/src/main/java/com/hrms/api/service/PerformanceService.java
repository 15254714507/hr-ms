package com.hrms.api.service;

import com.hrms.api.domain.entity.Performance;
import com.hrms.api.exception.DaoException;

/**
 * @author 孔超
 * @date 2020/4/30 21:08
 */
public interface PerformanceService {
    /**
     * 添加新的绩效信息
     *
     * @param performance
     * @return
     * @throws DaoException
     */
    public Long insert(Performance performance) throws DaoException;

}
