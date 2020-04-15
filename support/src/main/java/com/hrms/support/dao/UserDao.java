package com.hrms.support.dao;

import com.hrms.api.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/4/2 19:11
 */
@Mapper
public interface UserDao {
    /**
     * 根据id获取账号的信息
     *
     * @param id 用户id
     * @return 返回用户对象
     */
    public User getById(Long id);

    /**
     * 添加用户
     *
     * @param user 要添加的用户信息对象
     * @return 返回是否成功 1是成功，否则其他的都会报错
     */
    public Long insert(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public Long updateById(User user);

    /**
     * 删除此用户 逻辑删除
     *
     * @param id
     * @param updateTime 删除时的时间
     * @return
     */
    public Long deleteById(Long id, LocalDateTime updateTime);

    /**
     * 根据账号确定是否有此用户，包括已经删除的，因为建立唯一索引了
     *
     * @param username
     * @return
     */
    public User getByUsername(String username);
}
