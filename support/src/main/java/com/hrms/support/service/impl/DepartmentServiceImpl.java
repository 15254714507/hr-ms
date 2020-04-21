package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.DepartmentCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Department;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.DepartmentService;
import com.hrms.support.manager.DepartmentManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:08
 */
@Slf4j
@Service(interfaceClass = DepartmentService.class)
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    DepartmentManager departmentManager;

    @Override
    public Department getById(Long id) throws DaoException {
        return departmentManager.getById(id);
    }

    @Override
    public Employees getEmployees(Long departmentId) {
        Department department = departmentManager.getById(departmentId);
        if (department == null) {
            return null;
        }
        Employees employees = new Employees();
        employees.setDepartmentName(department.getDepartmentName());
        return employees;
    }

    @Override
    public List<Department> list(DepartmentCondition departmentCondition) throws DaoException {
        return departmentManager.list(departmentCondition);
    }
}
