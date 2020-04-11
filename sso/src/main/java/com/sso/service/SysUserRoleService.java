package com.sso.service;

import com.sso.domain.entity.SysUserRole;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 20:10
 */
public interface SysUserRoleService {
    /**
     * 根据用户的d获此用户的所有角色
     *
     * @param userId
     * @return
     */
    public List<SysUserRole> listByUserId(Long userId);
}
