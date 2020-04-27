package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.DimissionUserDao;
import com.hrms.support.manager.DimissionUserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/26 19:52
 */
@Slf4j
@Component("dimissionUserManager")
public class DimissionUserManagerImpl implements DimissionUserManager {
    @Resource
    DimissionUserDao dimissionUserDao;

    @Override
    public DimissionUser getById(Long id) throws DaoException {
        try {
            return dimissionUserDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(DimissionUser dimissionUser) throws DaoException {
        DimissionUserCondition dimissionUserCondition = getDimissionUserCondition(dimissionUser);
        List<DimissionUser> dimissionUserList = list(dimissionUserCondition);
        if (dimissionUserList != null && dimissionUserList.size() > 0) {
            log.warn("新添加的离职，员工编码已经存在 dimissionUser：{}", JSON.toJSONString(dimissionUser));
            return 0L;
        }
        dimissionUser.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        dimissionUser.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return dimissionUserDao.insert(dimissionUser);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(DimissionUser dimissionUser) throws DaoException {
        if (getById(dimissionUser.getId()) == null) {
            log.warn("要修改的离职员工不存在 dimissionUser：{}", JSON.toJSONString(dimissionUser));
            return 0L;
        }
        dimissionUser.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return dimissionUserDao.updateById(dimissionUser);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的离职的不存在 id：{}", id);
            return 0L;
        }
        try {
            return dimissionUserDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<DimissionUser> list(DimissionUserCondition dimissionUserCondition) throws DaoException {
        try {
            return dimissionUserDao.list(dimissionUserCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * DimissionUser转换成DimissionUserCondition
     *
     * @param dimissionUser
     * @return
     */
    private DimissionUserCondition getDimissionUserCondition(DimissionUser dimissionUser) {
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setUsername(dimissionUser.getUsername());
        return dimissionUserCondition;
    }
}
