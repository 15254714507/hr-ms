package com.hrms.api.service;

import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.User;
import com.hrms.api.exception.DaoException;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
public interface UserService {
    /**
     * 根据id获得用户的信息
     *
     * @param id
     * @return
     */
    public User getById(Long id);

    /**
     * 添加用户
     *
     * @param user 添加的用户
     * @return 返回是否成功，1是成功 0是已存在此账号
     * @throws DaoException 包装的异常
     */
    public Long insert(User user) throws DaoException;

    /**
     * 通过账号密码获得账号信息
     *
     * @param userCondition
     * @return
     */
    public User getUserByUserNamePassword(UserCondition userCondition);

    /**
     * 根据账号确定是否有此用户，包括已经删除的，因为根据账号建立唯一索引了
     *
     * @param username 用户账号
     * @return 返回此账号的用户信息
     * @throws DaoException 包装了一成异常
     */
    public User getByUsername(String username) throws DaoException;
    /**
     * 删除此用户 逻辑删除
     *
     * @param id
     * @return 返回是否成功  1是成功  0是没有此用户(要不不存在，要不逻辑删除已经删除了)
     * @throws DaoException 包装了一层异常
     */
    public Long deleteById(Long id) throws DaoException;
}
