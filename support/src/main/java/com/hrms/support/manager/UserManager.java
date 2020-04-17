package com.hrms.support.manager;


import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.User;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
public interface UserManager {
    /**
     * 根据id获取账号的信息
     *
     * @param id 主键
     * @return 返回id对应的用户信息
     * @throws DaoException 包装的异常
     */
    public User getById(Long id) throws DaoException;

    /**
     * 添加用户
     *
     * @param user 添加的用户
     * @return 返回是否成功，1是成功 0是已存在此账号
     * @throws DaoException 包装的异常
     */
    public Long insert(User user) throws DaoException;

    /**
     * 修改用户信息
     *
     * @param user 要修改的用户信息，id存在
     * @return 返回是否成功 1是成功 0是没有此用户信息(可能不存在或者 被逻辑删除了)
     * @throws DaoException 包装了一层异常
     */
    public Long updateById(User user) throws DaoException;

    /**
     * 删除此用户 逻辑删除
     *
     * @param id
     * @return 返回是否成功  1是成功  2是没有此用户(要不不存在，要不逻辑删除已经删除了)
     * @throws DaoException 包装了一层异常
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 根据账号确定是否有此用户，包括已经删除的，因为根据账号建立唯一索引了
     *
     * @param username 用户账号
     * @return 返回此账号的用户信息
     * @throws DaoException 包装了一成异常
     */
    public User getByUsername(String username) throws DaoException;

    /**
     * 获得用户的集合
     *
     * @param userCondition 搜索的条件
     * @return 返回user的集合
     * @throws DaoException 包装了一成异常
     */
    public List<User> list(UserCondition userCondition) throws DaoException;
}
