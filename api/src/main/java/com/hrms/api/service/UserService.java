package com.hrms.api.service;

import com.hrms.api.domain.entity.User;

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
}
