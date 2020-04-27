package com.hrms.api.service;

import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.exception.DaoException;
import com.hrms.api.exception.ServiceException;
import com.hrms.api.until.Result;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/27 18:37
 */
public interface DimissionUserService {
    /**
     * 根据账号获得这个账号的离职需要的信息
     *
     * @param username
     * @return
     */
    public DimissionUser getDimissionUserMsgByUsername(String username);

    /**
     * 添加离职员工
     *
     * @param dimissionUser
     * @return
     * @throws ServiceException
     */
    public Result insert(DimissionUser dimissionUser);
    /**
     * 修改离职用户的信息
     *
     * @param dimissionUser
     * @return
     * @throws DaoException
     */
    public Long updateById(DimissionUser dimissionUser) throws DaoException;
    /**
     * 删除离职用户
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Result deleteById(Long id) throws DaoException;

    /**
     * 离职用户的集合
     *
     * @param dimissionUserCondition
     * @return
     * @throws DaoException
     */
    public List<DimissionUser> list(DimissionUserCondition dimissionUserCondition) throws DaoException;
}
