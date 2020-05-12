package com.hrms.support.manager.impl;

import com.hrms.api.domain.condition.RecruitersCondition;
import com.hrms.api.domain.entity.Recruiters;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.RecruitersDao;
import com.hrms.support.manager.RecruitersManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/12 13:45
 */
@Slf4j
@Component("recruitersManager")
public class RecruitersManagerImpl implements RecruitersManager {
    @Resource
    RecruitersDao recruitersDao;

    @Override
    public Recruiters getById(Long id) throws DaoException {
        try {
            return recruitersDao.getById(id);
        } catch (Exception e) {
            throw new DaoException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Recruiters recruiters) throws DaoException {
        recruiters.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        recruiters.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return recruitersDao.insert(recruiters);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(Recruiters recruiters) throws DaoException {
        if (getById(recruiters.getId()) == null) {
            log.warn("要修改的应聘者不存在");
            return 0L;
        }
        recruiters.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return recruitersDao.updateById(recruiters);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的应聘者不存在");
            return 0L;
        }
        try {
            return recruitersDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Recruiters> list(RecruitersCondition recruitersCondition) throws DaoException {
        try {
            return recruitersDao.list(recruitersCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
