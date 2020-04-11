package com.sso.service;

import com.sso.domain.entity.SysPermission;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/9 15:00
 */
public interface SysPermissionService {
    /**
     * 获得此账号的权限
     * @param id
     * @return
     */
    public List<SysPermission> findByUserId(Long id);
}
