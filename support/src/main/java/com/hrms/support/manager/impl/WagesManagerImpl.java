package com.hrms.support.manager.impl;

import com.hrms.api.domain.condition.WagesCondition;
import com.hrms.api.domain.entity.Wages;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.WagesDao;
import com.hrms.support.manager.WagesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/6 2:58
 */
@Slf4j
@Component("wagesManager")
public class WagesManagerImpl implements WagesManager {
    @Resource
    WagesDao wagesDao;

    @Override
    public Wages getById(Long id) throws DaoException {
        try {
            return wagesDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Wages wages) throws DaoException {
        WagesCondition wagesCondition = getWagesCondition(wages);
        List<Wages> wagesList = list(wagesCondition);
        if (wagesList != null && wagesList.size() > 0) {
            log.warn("要添加的新的工资信息在当月已存在 wages{}", wages);
            return 0L;
        }
        wages.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        wages.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return wagesDao.insert(wages);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(Wages wages) throws DaoException {
        if (getById(wages.getId()) == null) {
            log.warn("要修改的工资信息不存在 wages{}", wages);
            return 0L;
        }
        wages.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return wagesDao.updateById(wages);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的工资信息不存在 id{}", id);
            return 0L;
        }
        try {
            return wagesDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Wages> list(WagesCondition wagesCondition) throws DaoException {
        try {
            return wagesDao.list(wagesCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Wages> listByYearMonth(WagesCondition wagesCondition) throws DaoException {
        return list(wagesCondition);
    }

    /**
     * wages转为条件WagesCondition用于list
     *
     * @param wages
     * @return
     */
    private WagesCondition getWagesCondition(Wages wages) {
        WagesCondition wagesCondition = new WagesCondition();
        wagesCondition.setUsername(wages.getUsername());
        wagesCondition.setYear(wages.getWagesDate().getYear());
        wagesCondition.setMonth(wages.getWagesDate().getMonthValue());
        return wagesCondition;
    }
}
