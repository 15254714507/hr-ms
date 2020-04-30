package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.PerformanceDao;
import com.hrms.support.manager.PerformanceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/30 2:24
 */
@Slf4j
@Component("performanceManager")
public class PerformanceManagerImpl implements PerformanceManager {
    @Resource
    PerformanceDao performanceDao;

    @Override
    public Performance getById(Long id) throws DaoException {
        try {
            return performanceDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Performance performance) throws DaoException {
        performance.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        performance.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return performanceDao.insert(performance);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(Performance performance) throws DaoException {
        if (getById(performance.getId()) == null) {
            log.warn("要修改的绩效信息不存在 performance{}", JSON.toJSONString(performance));
            return 0L;
        }
        performance.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return performanceDao.updateById(performance);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的绩效信息不存在 id{}", id);
            return 0L;
        }
        try {
            return performanceDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Performance> list(PerformanceCondition performanceCondition) throws DaoException {
        try {
            return performanceDao.list(performanceCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Performance> listByYearMonth(PerformanceCondition performanceCondition) throws DaoException {
        return list(performanceCondition);
    }
}
