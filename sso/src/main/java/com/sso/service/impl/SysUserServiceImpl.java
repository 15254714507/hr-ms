package com.sso.service.impl;

import com.sso.dao.SysUserDao;
import com.sso.domain.entity.SysUser;
import com.sso.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 孔超
 * @date 2020/4/9 15:16
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserDao sysUserDao;

    @Override
    public SysUser getByUseName(String userName) {
        return sysUserDao.getByUseName(userName);
    }
}
