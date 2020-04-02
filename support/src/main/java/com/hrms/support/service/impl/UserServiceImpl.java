package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.UserService;
import com.hrms.support.manager.UserManager;

import javax.annotation.Resource;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Resource
    UserManager userManager;

    @Override
    public User getById(Long id) {
        return userManager.getById(id);
    }
}
