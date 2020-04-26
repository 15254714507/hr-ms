package com.hrms.support.manager;

import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/26 19:47
 */
public interface DimissionUserManager {
    /**
     * 根据id获得离职用户的信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public DimissionUser getById(Long id) throws DaoException;

    /**
     * 添加新的离职用户
     *
     * @param dimissionUser
     * @return
     * @throws DaoException
     */
    public Long insert(DimissionUser dimissionUser) throws DaoException;

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
    public Long deleteById(Long id) throws DaoException;

    /**
     * 离职用户的集合
     *
     * @param dimissionUserCondition
     * @return
     * @throws DaoException
     */
    public List<DimissionUser> list(DimissionUserCondition dimissionUserCondition) throws DaoException;
}
