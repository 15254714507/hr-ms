package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.PerformanceService;
import com.hrms.support.manager.PerformanceManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author 孔超
 * @date 2020/4/30 21:08
 */
@Slf4j
@Service(interfaceClass = PerformanceService.class)
public class PerformanceServiceImpl implements PerformanceService {
    @Resource
    PerformanceManager performanceManager;

    @Override
    public Long insert(Performance performance) throws DaoException {
        return performanceManager.insert(performance);
    }
}
