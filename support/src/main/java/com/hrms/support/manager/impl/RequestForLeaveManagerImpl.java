package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.RequestForLeaveDao;
import com.hrms.support.manager.RequestForLeaveManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/3 22:43
 */
@Slf4j
@Component("requestForLeaveManager")
public class RequestForLeaveManagerImpl implements RequestForLeaveManager {
    @Resource
    RequestForLeaveDao requestForLeaveDao;

    @Override
    public RequestForLeave getById(Long id) throws DaoException {
        try {
            return requestForLeaveDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(RequestForLeave requestForLeave) throws DaoException {
        RequestForLeaveCondition requestForLeaveCondition = getRequestForLeaveCondition(requestForLeave);
        List<RequestForLeave> requestForLeaveList = list(requestForLeaveCondition);
        if (requestForLeaveList != null && requestForLeaveList.size() > 0) {
            log.warn("新的请假申请时发现相同时间已有请假申请 requestForLeave{}", JSON.toJSONString(requestForLeave));
            return 0L;
        }
        requestForLeave.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        requestForLeave.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return requestForLeaveDao.insert(requestForLeave);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(RequestForLeave requestForLeave) throws DaoException {
        if (getById(requestForLeave.getId()) == null) {
            log.warn("要修改的假期申请不存在requestForLeave{}", JSON.toJSONString(requestForLeave));
            return 0L;
        }
        requestForLeave.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return requestForLeaveDao.updateById(requestForLeave);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的请假申请不存在 id{}", id);
            return 0L;
        }
        try {
            return requestForLeaveDao.deleteById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<RequestForLeave> list(RequestForLeaveCondition requestForLeaveCondition) throws DaoException {
        try {
            return requestForLeaveDao.list(requestForLeaveCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * requestForLeave转成requestForLeaveCondition用于查询
     *
     * @param requestForLeave
     * @return
     */
    private RequestForLeaveCondition getRequestForLeaveCondition(RequestForLeave requestForLeave) {
        RequestForLeaveCondition requestForLeaveCondition = new RequestForLeaveCondition();
        requestForLeaveCondition.setUsername(requestForLeave.getUsername());
        requestForLeaveCondition.setStartDate(requestForLeave.getStartDate());
        requestForLeaveCondition.setEndDate(requestForLeave.getEndDate());
        return requestForLeaveCondition;
    }
}
