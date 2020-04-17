package com.hrms.support.manager;

import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:06
 */
public interface UserJobManager {
    /**
     * 根据id获得用户和岗位的对应关系
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public UserJob getById(Long id) throws DaoException;

    /**
     * 添加新用户和岗位的对应关系
     *
     * @param userJob
     * @return
     * @throws DaoException
     */
    public Long insert(UserJob userJob) throws DaoException;

    /**
     * 修改岗位信息
     *
     * @param userJob
     * @return
     * @throws DaoException
     */
    public Long updateById(UserJob userJob) throws DaoException;

    /**
     * 删除岗位信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得岗位信息的集合
     *
     * @param userJobCondition
     * @return
     * @throws DaoException
     */
    public List<UserJob> list(UserJobCondition userJobCondition) throws DaoException;
}
