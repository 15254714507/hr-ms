package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.UserJobDao;
import com.hrms.support.manager.UserJobManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:18
 */
@Slf4j
@Component("userJobManager")
public class UserJobManagerImpl implements UserJobManager {
    @Resource
    UserJobDao userJobDao;

    @Override
    public UserJob getById(Long id) throws DaoException {
        try {
            return userJobDao.getById(id);
        } catch (Exception e) {
            throw new DaoException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(UserJob userJob) throws DaoException {
        UserJobCondition userJobCondition = getUserJobCondition(userJob);
        List<UserJob> userJobList = userJobDao.list(userJobCondition);
        if (userJobList != null && userJobList.size() > 0) {
            log.warn("要添加的用户和岗位已经存在 userJob：{}", JSON.toJSONString(userJob));
            return 0L;
        }
        userJob.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        userJob.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return userJobDao.insert(userJob);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(UserJob userJob) throws DaoException {
        if (getById(userJob.getId()) == null) {
            log.warn("要修改的用户和职位对应关系不存在 userJob{}", JSON.toJSONString(userJob));
            return 0L;
        }
        UserJobCondition userJobCondition = getUserJobCondition(userJob);
        List<UserJob> userJobList = userJobDao.list(userJobCondition);
        if (userJobList != null && userJobList.size() > 0) {
            log.warn("要修改成的用户和职位对应关系已存在 userJob：{}", JSON.toJSONString(userJob));
            return 0L;
        }
        userJob.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return userJobDao.updateById(userJob);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的用户和岗位对应关系不存在你 id：{}", id);
            return 0L;
        }
        try {
            return userJobDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<UserJob> list(UserJobCondition userJobCondition) throws DaoException {
        try {
            return userJobDao.list(userJobCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * userJob转换成UserJobCondition
     *
     * @param userJob
     * @return
     */
    private UserJobCondition getUserJobCondition(UserJob userJob) {
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setUserId(userJob.getUserId());
        userJobCondition.setJobId(userJob.getJobId());
        return userJobCondition;
    }
}
