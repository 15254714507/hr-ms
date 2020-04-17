package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.User;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.UserDao;
import com.hrms.support.manager.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@Slf4j
@Component("userManager")
public class UserManagerImpl implements UserManager {
    @Resource
    UserDao userDao;

    @Override
    public User getById(Long id) throws DaoException {
        try {
            return userDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(User user) throws DaoException {
        if (getByUsername(user.getUsername()) != null) {
            log.warn("添加的新用户，账号已存在 user:{}", JSON.toJSONString(user));
            return 0L;
        }
        user.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        user.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return userDao.insert(user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(User user) throws DaoException {
        if (getById(user.getId()) == null) {
            log.warn("要修改的用户信息不存在 user:{}", JSON.toJSONString(user));
            return 0L;
        }
        user.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return userDao.updateById(user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的的用户不存在 id:{}", id);
            return 0L;
        }
        try {
            return userDao.deleteById(id, LocalDateTimeFactory.getLocalDateTime());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public User getByUsername(String username) throws DaoException {
        try {
            return userDao.getByUsername(username);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> list(UserCondition userCondition) throws DaoException {
        try {
            return userDao.list(userCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
