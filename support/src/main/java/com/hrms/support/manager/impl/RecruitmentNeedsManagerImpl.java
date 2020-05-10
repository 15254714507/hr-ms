package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.RecruitmentNeedsCondition;
import com.hrms.api.domain.entity.RecruitmentNeeds;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.RecruitmentNeedsDao;
import com.hrms.support.manager.RecruitmentNeedsManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/10 17:09
 */
@Slf4j
@Component("recruitmentNeedsManager")
public class RecruitmentNeedsManagerImpl implements RecruitmentNeedsManager {
    @Resource
    RecruitmentNeedsDao recruitmentNeedsDao;

    @Override
    public RecruitmentNeeds getById(Long id) throws DaoException {
        try {
            return recruitmentNeedsDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(RecruitmentNeeds recruitmentNeeds) throws DaoException {
        recruitmentNeeds.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        recruitmentNeeds.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return recruitmentNeedsDao.insert(recruitmentNeeds);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(RecruitmentNeeds recruitmentNeeds) throws DaoException {
        if (getById(recruitmentNeeds.getId()) == null) {
            log.warn("要修改的招聘需求不存在recruitmentNeeds{}", JSON.toJSONString(recruitmentNeeds));
            return 0L;
        }
        recruitmentNeeds.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return recruitmentNeedsDao.updateById(recruitmentNeeds);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的招聘需求不存在id{}", id);
            return 0L;
        }
        try {
            return recruitmentNeedsDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<RecruitmentNeeds> list(RecruitmentNeedsCondition recruitmentNeedsCondition) throws DaoException {
        try {
            return recruitmentNeedsDao.list(recruitmentNeedsCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
