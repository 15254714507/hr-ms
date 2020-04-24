package com.hrms.api.service;

import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.exception.DaoException;
import com.hrms.api.exception.ServiceException;
import com.hrms.api.until.Result;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/21 19:25
 */
public interface RegisterNewEmployeeService {
    /**
     * 根据id获取审核的新员工的信息
     *
     * @param id 主键
     * @return 返回id对应的用户信息
     * @throws DaoException 包装的异常
     */
    public RegisterNewEmployee getById(Long id) throws DaoException;

    /**
     * 添加待审核新员工
     *
     * @param registerNewEmployee 新员工的所有信息
     * @return 返回执行后的信息
     */
    public Result insert(RegisterNewEmployee registerNewEmployee);

    /**
     * 修改需要审核的新员工的信息
     *
     * @param registerNewEmployee 要修改的用户信息，id存在
     * @return 返回是否成功 1是成功 0是没有此用户信息(可能不存在或者 被逻辑删除了)
     * @throws DaoException 包装了一层异常
     */
    public Long updateById(RegisterNewEmployee registerNewEmployee) throws DaoException;

    /**
     * 获得待审核员工信息的集合
     *
     * @param registerNewEmployeeCondition
     * @return
     * @throws DaoException
     */
    public List<RegisterNewEmployee> list(RegisterNewEmployeeCondition registerNewEmployeeCondition) throws DaoException;

    /**
     * 待审核员工通过了的服务
     *
     * @param registerNewEmployee
     * @return
     * @throws ServiceException
     */
    public Result pass(RegisterNewEmployee registerNewEmployee) throws ServiceException, DaoException;
}
