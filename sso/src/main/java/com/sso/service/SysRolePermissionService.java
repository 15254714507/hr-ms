package com.sso.service;

import com.sso.domain.entity.SysRolePermission;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 19:00
 */
public interface SysRolePermissionService {
    /**
     * 根据用户id的获得此用户所有的角色对应的权限的集合
     *
     * @param userId 用户的id
     * @return
     */
    public List<SysRolePermission> listByUserId(Long userId);
}
