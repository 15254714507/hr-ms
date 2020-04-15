package com.hrms.support.manager;

import com.hrms.api.domain.condition.DepartmentCondition;
import com.hrms.api.domain.entity.Department;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:07
 */
public interface DepartmentManager {
    /**
     * 根据id获得部门信息
     *
     * @param id 主键
     * @return 返回部门对象
     * @throws DaoException 包装的一层异常
     */
    public Department getById(Long id) throws DaoException;

    /**
     * 添加新的部门
     *
     * @param department 要添加的部门对象
     * @return 返回是否成功 1是成功 0已存在此部门
     * @throws DaoException 包装的一层异常
     */
    public Long insert(Department department) throws DaoException;

    /**
     * 修改部门信息
     *
     * @param department 要修改的部门对象，里面有id
     * @return 返回是否成功 1代表成功 0是要修改的部门不存在
     * @throws DaoException 包装的一层异常
     */
    public Long updateById(Department department) throws DaoException;

    /**
     * 删除部门  是真删除，不是逻辑删除
     *
     * @param id 要删除的部门的id
     * @return 返回是否删除成功 1成功  0不存在要删除的部门
     * @throws DaoException 包装的一层异常
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 根据条件获得想要的部门的集合
     *
     * @param departmentCondition 条件对象
     * @return 返回部门的集合
     * @throws DaoException 包装的一层异常
     */
    public List<Department> list(DepartmentCondition departmentCondition) throws DaoException;
}
