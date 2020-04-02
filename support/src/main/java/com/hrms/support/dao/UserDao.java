package com.hrms.support.dao;

import com.hrms.api.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 孔超
 * @date 2020/4/2 19:11
 */
@Mapper
public interface UserDao {
    /**
     * 根据id获取账号的信息
     *
     * @param id
     * @return
     */
    public User getById(Long id);
}
