package com.sso.service.impl;

import com.sso.dao.SysRolePermissionDao;
import com.sso.domain.entity.SysRole;
import com.sso.domain.entity.SysRolePermission;
import com.sso.domain.entity.SysUserRole;
import com.sso.service.SysRolePermissionService;
import com.sso.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孔超
 * @date 2020/4/10 19:01
 */
@Slf4j
@Service("sysRolePermissionService")
public class SysRolePermissionImpl implements SysRolePermissionService {
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysRolePermissionDao sysRolePermissionDao;

    @Override
    public List<SysRolePermission> listByUserId(Long userId) {
        List<SysRole> sysRoleList = sysRoleService.listByUserId(userId);
        if (CollectionUtils.isEmpty(sysRoleList)) {
            return null;
        }
        List<Long> roleIdList = sysRoleList.stream().map(SysRole::getId).collect(Collectors.toList());
        return sysRolePermissionDao.listByRoleIds(roleIdList);
    }
}
