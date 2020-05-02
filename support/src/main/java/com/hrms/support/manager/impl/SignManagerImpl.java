package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.SignDao;
import com.hrms.support.manager.SignManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/2 22:03
 */
@Slf4j
@Component("signManager")
public class SignManagerImpl implements SignManager {
    @Resource
    SignDao signDao;

    @Override
    public Sign getById(Long id) throws DaoException {
        try {
            return signDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Sign sign) throws DaoException {
        SignCondition signCondition = getSignCondition(sign);
        List<Sign> signList = listByYearMonthDay(signCondition);
        if (signList != null && signList.size() > 0) {
            log.warn("签到表里已经此账号今天的签到记录了signCondition{}", JSON.toJSONString(signCondition));
            return 0L;
        }
        sign.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        sign.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return signDao.insert(sign);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(Sign sign) throws DaoException {
        if (getById(sign.getId()) == null) {
            log.warn("要修改的签到信息不存在 sign{}", JSON.toJSONString(sign));
            return 0L;
        }
        sign.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return signDao.updateById(sign);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("删除的签到信息不存在 id{}", id);
            return 0L;
        }
        try {
            return signDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Sign> list(SignCondition signCondition) throws DaoException {
        try {
            return signDao.list(signCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Sign> listByYearMonthDay(SignCondition signCondition) throws DaoException {
        return list(signCondition);
    }

    private SignCondition getSignCondition(Sign sign) {
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername(sign.getUsername());
        signCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        signCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonthValue());
        signCondition.setDay(LocalDateTimeFactory.getLocalDate().getDayOfMonth());
        return signCondition;
    }
}
