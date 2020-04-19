package com.hrms.api.service;

import com.hrms.api.domain.condition.DepartmentCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Department;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:07
 */
public interface DepartmentService {
    /**
     * 获得职员的部门信息
     *
     * @param departmentId
     * @return
     */
    public Employees getEmployees(Long departmentId);

    /**
     * 根据条件获得想要的部门的集合
     *
     * @param departmentCondition 条件对象
     * @return 返回部门的集合
     * @throws DaoException 包装的一层异常
     */
    public List<Department> list(DepartmentCondition departmentCondition) throws DaoException;
}
