package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.OnboardingLeavingTrendCondition;
import com.hrms.api.domain.entity.OnboardingLeavingTrend;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.OnboardingLeavingTrendDao;
import com.hrms.support.manager.OnboardingLeavingTrendManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/6/1 23:42
 */
@Slf4j
@Component("onboardingLeavingTrendManager")
public class OnboardingLeavingTrendManagerImpl implements OnboardingLeavingTrendManager {
    @Resource
    OnboardingLeavingTrendDao onboardingLeavingTrendDao;

    @Override
    public OnboardingLeavingTrend getById(Long id) throws DaoException {
        try {
            return onboardingLeavingTrendDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException {
        onboardingLeavingTrend.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        onboardingLeavingTrend.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return onboardingLeavingTrendDao.insert(onboardingLeavingTrend);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException {
        if (getById(onboardingLeavingTrend.getId()) == null) {
            log.warn("没有此入职离职数量记录 OnboardingLeavingTrend{}", JSON.toJSONString(onboardingLeavingTrend));
            return 0L;
        }
        onboardingLeavingTrend.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return onboardingLeavingTrendDao.updateById(onboardingLeavingTrend);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("没有此入职离职数量记录 id{}", id);
            return 0L;
        }
        try {
            return onboardingLeavingTrendDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<OnboardingLeavingTrend> list(OnboardingLeavingTrendCondition onboardingLeavingTrendCondition) throws DaoException {
        try {
            return onboardingLeavingTrendDao.list(onboardingLeavingTrendCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
