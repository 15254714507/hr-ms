package com.hrms.support.manager;


import com.hrms.api.domain.entity.User;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
public interface UserManager {
    /**
     * 根据id获取账号的信息
     *
     * @param id
     * @return
     */
    public User getById(Long id);
}
