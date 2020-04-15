package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.DepartmentCondition;
import com.hrms.api.domain.entity.Department;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.DepartmentDao;
import com.hrms.support.manager.DepartmentManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:17
 */
@Slf4j
@Component("departmentManager")
public class DepartmentManagerImpl implements DepartmentManager {
    @Resource
    DepartmentDao departmentDao;

    @Override
    public Department getById(Long id) throws DaoException {
        try {
            return departmentDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Department department) throws DaoException {
        DepartmentCondition departmentCondition = getDepartmentCondition(department);
        List<Department> departmentList = list(departmentCondition);
        if (departmentList != null && departmentList.size() > 0) {
            log.warn("要添加的部门已存在 department:{}", JSON.toJSONString(department));
            return 0L;
        }
        department.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        department.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return departmentDao.insert(department);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(Department department) throws DaoException {
        if (getById(department.getId()) == null) {
            log.warn("要修改的部门不存在 department{}", JSON.toJSONString(department));
            return 0L;
        }
        DepartmentCondition departmentCondition = getDepartmentCondition(department);
        List<Department> departmentList = list(departmentCondition);
        if (departmentList != null && departmentList.size() > 0) {
            log.warn("有修改的部门名称已存在 department{}", JSON.toJSONString(department));
            return 0L;
        }
        department.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return departmentDao.updateById(department);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的部门不存在 id：{}", id);
            return 0L;
        }
        try {
            return departmentDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Department> list(DepartmentCondition departmentCondition) throws DaoException {
        try {
            return departmentDao.list(departmentCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Department转到DepartmentCondition
     *
     * @param department
     * @return
     */
    private DepartmentCondition getDepartmentCondition(Department department) {
        DepartmentCondition departmentCondition = new DepartmentCondition();
        departmentCondition.setDepartmentName(department.getDepartmentName());
        return departmentCondition;
    }
}
