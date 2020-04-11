package com.sso.service.impl;

import com.sso.dao.SysUserRoleDao;
import com.sso.domain.entity.SysUserRole;
import com.sso.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 20:29
 */
@Slf4j
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    SysUserRoleDao sysUserRoleDao;

    @Override
    public List<SysUserRole> listByUserId(Long userId) {
        return sysUserRoleDao.listByUserId(userId);
    }
}
