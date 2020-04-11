package com.sso.service.impl;

import com.sso.dao.SysPermissionDao;
import com.sso.domain.entity.SysPermission;
import com.sso.domain.entity.SysRole;
import com.sso.domain.entity.SysRolePermission;
import com.sso.service.SysPermissionService;
import com.sso.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孔超
 * @date 2020/4/9 16:59
 */
@Slf4j
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    SysRolePermissionService sysRolePermissionService;
    @Resource
    SysPermissionDao sysPermissionDao;

    @Override
    public List<SysPermission> findByUserId(Long userId) {
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionService.listByUserId(userId);
        if (CollectionUtils.isEmpty(sysRolePermissionList)) {
            return null;
        }
        List<Long> permissionIdList = sysRolePermissionList.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList());
        return sysPermissionDao.listByPermissionIds(permissionIdList);
    }
}
