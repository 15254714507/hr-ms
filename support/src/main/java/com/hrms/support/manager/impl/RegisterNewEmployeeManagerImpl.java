package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.RegisterNewEmployeeDao;
import com.hrms.support.manager.RegisterNewEmployeeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/20 17:43
 */
@Slf4j
@Component("registerNewEmployeeManager")
public class RegisterNewEmployeeManagerImpl implements RegisterNewEmployeeManager {
    @Resource
    RegisterNewEmployeeDao registerNewEmployeeDao;

    @Override
    public RegisterNewEmployee getById(Long id) throws DaoException {
        try {
            return registerNewEmployeeDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(RegisterNewEmployee registerNewEmployee) throws DaoException {
        RegisterNewEmployeeCondition registerNewEmployeeCondition = getRegisterNewEmployeeCondition(registerNewEmployee);
        List<RegisterNewEmployee> registerNewEmployeeList = list(registerNewEmployeeCondition);
        if (registerNewEmployeeList != null && registerNewEmployeeList.size() > 0) {
            log.warn("新添加待审核的新员工账号已存在 registerNewEmployee:{}", JSON.toJSONString(registerNewEmployee));
            return 0L;
        }
        registerNewEmployee.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        registerNewEmployee.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return registerNewEmployeeDao.insert(registerNewEmployee);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(RegisterNewEmployee registerNewEmployee) throws DaoException {
        if (getById(registerNewEmployee.getId()) == null) {
            log.warn("要修改的待审核员工不存在 registerNewEmployee：{}", JSON.toJSONString(registerNewEmployee));
            return 0L;
        }
        RegisterNewEmployeeCondition registerNewEmployeeCondition = getRegisterNewEmployeeCondition(registerNewEmployee);
        List<RegisterNewEmployee> registerNewEmployeeList = list(registerNewEmployeeCondition);
        if (registerNewEmployeeList != null && registerNewEmployeeList.size() > 0) {
            log.warn("要修改后的员工编码也已存在，registerNewEmployee：{}", JSON.toJSONString(registerNewEmployee));
            return 0L;
        }
        registerNewEmployee.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return registerNewEmployeeDao.updateById(registerNewEmployee);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的待审核的职员不存在 id：{}", id);
            return 0L;
        }
        try {
            return registerNewEmployeeDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<RegisterNewEmployee> list(RegisterNewEmployeeCondition registerNewEmployeeCondition) throws DaoException {
        try {
            return registerNewEmployeeDao.list(registerNewEmployeeCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * registerNewEmployee转成RegisterNewEmployeeCondition
     *
     * @param registerNewEmployee
     * @return
     */
    private RegisterNewEmployeeCondition getRegisterNewEmployeeCondition(RegisterNewEmployee registerNewEmployee) {
        RegisterNewEmployeeCondition registerNewEmployeeCondition = new RegisterNewEmployeeCondition();
        registerNewEmployeeCondition.setUsername(registerNewEmployee.getUsername());
        return registerNewEmployeeCondition;
    }
}
