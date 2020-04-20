package com.hrms.support.manager;

import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/20 17:43
 */
public interface RegisterNewEmployeeManager {
    /**
     * 根据id获取审核的新员工的信息
     *
     * @param id 主键
     * @return 返回id对应的用户信息
     * @throws DaoException 包装的异常
     */
    public RegisterNewEmployee getById(Long id) throws DaoException;

    /**
     * 添加需要审核的新员工的信心
     *
     * @param registerNewEmployee 添加的用户
     * @return 返回是否成功，1是成功 0是已存在此账号
     * @throws DaoException 包装的异常
     */
    public Long insert(RegisterNewEmployee registerNewEmployee) throws DaoException;

    /**
     * 修改需要审核的新员工的信息
     *
     * @param registerNewEmployee 要修改的用户信息，id存在
     * @return 返回是否成功 1是成功 0是没有此用户信息(可能不存在或者 被逻辑删除了)
     * @throws DaoException 包装了一层异常
     */
    public Long updateById(RegisterNewEmployee registerNewEmployee) throws DaoException;

    /**
     * 删除需要审核的新员工
     *
     * @param id
     * @return 返回是否成功  1是成功  2是没有此用户(要不不存在，要不逻辑删除已经删除了)
     * @throws DaoException 包装了一层异常
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得需要审核的新员工的集合
     *
     * @param registerNewEmployeeCondition 搜索的条件
     * @return 返回user的集合
     * @throws DaoException 包装了一成异常
     */
    public List<RegisterNewEmployee> list(RegisterNewEmployeeCondition registerNewEmployeeCondition) throws DaoException;
}
