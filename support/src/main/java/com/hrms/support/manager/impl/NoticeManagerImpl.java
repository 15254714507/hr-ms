package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.NoticeCondition;
import com.hrms.api.domain.entity.Notice;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.NoticeDao;
import com.hrms.support.manager.NoticeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/30 23:44
 */
@Slf4j
@Component("noticeManager")
public class NoticeManagerImpl implements NoticeManager {
    @Resource
    NoticeDao noticeDao;

    @Override
    public Notice getById(Long id) throws DaoException {
        try {
            return noticeDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Notice notice) throws DaoException {
        notice.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        notice.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return noticeDao.insert(notice);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(Notice notice) throws DaoException {
        if (getById(notice.getId()) == null) {
            log.warn("要修改的通知不存在 notice{}", JSON.toJSONString(notice));
            return 0L;
        }
        notice.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return noticeDao.updateById(notice);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的通知不存在 id{}", id);
            return 0L;
        }
        try {
            return noticeDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Notice> list(NoticeCondition noticeCondition) throws DaoException {
        try {
            return noticeDao.list(noticeCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
