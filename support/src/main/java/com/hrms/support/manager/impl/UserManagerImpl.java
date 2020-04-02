package com.hrms.support.manager.impl;

import com.hrms.api.domain.entity.User;
import com.hrms.support.dao.UserDao;
import com.hrms.support.manager.UserManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@Component("userManager")
public class UserManagerImpl implements UserManager {
    @Resource
    UserDao userDao;

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
