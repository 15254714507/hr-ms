package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.User;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.UserService;
import com.hrms.support.manager.UserManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@Slf4j
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Resource
    UserManager userManager;

    @Override
    public User getById(Long id) {
        return userManager.getById(id);
    }

    @Override
    public Long insert(User user) throws DaoException {
        return userManager.insert(user);
    }

    @Override
    public Long updateById(User user) throws DaoException {
        return userManager.updateById(user);
    }

    @Override
    public User getUserByUserNamePassword(UserCondition userCondition) {
        List<User> userList = userManager.list(userCondition);
        if (userList == null || userList.size() < 1) {
            return null;
        }
        if (userList.get(0).getPassword().equals(userCondition.getPassword())) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User getByUsername(String username) throws DaoException {
        return userManager.getByUsername(username);
    }

    @Override
    public Long deleteById(Long id) throws DaoException {
        return userManager.deleteById(id);
    }

    @Override
    public List<User> list(UserCondition userCondition) throws DaoException {
        return userManager.list(userCondition);
    }
}
