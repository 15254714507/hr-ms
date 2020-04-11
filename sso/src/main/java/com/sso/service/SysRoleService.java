package com.sso.service;

import com.sso.domain.entity.SysRole;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 20:09
 */
public interface SysRoleService {
    /**
     * 根据 用户id获得所有的角色的集合
     *
     * @param userId
     * @return
     */
    public List<SysRole> listByUserId(Long userId);
}
