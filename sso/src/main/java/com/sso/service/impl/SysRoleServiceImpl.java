package com.sso.service.impl;

import com.sso.dao.SysRoleDao;
import com.sso.domain.entity.SysRole;
import com.sso.domain.entity.SysUserRole;
import com.sso.service.SysRoleService;
import com.sso.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孔超
 * @date 2020/4/10 20:34
 */
@Slf4j
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    SysUserRoleService sysUserRoleService;
    @Resource
    SysRoleDao sysRoleDao;

    @Override
    public List<SysRole> listByUserId(Long userId) {
        List<SysUserRole> sysUserRoleList = sysUserRoleService.listByUserId(userId);
        if (CollectionUtils.isEmpty(sysUserRoleList)) {
            return null;
        }
        List<Long> roleIdList = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        //既然已经得到roleIds为什么不直接通过SysRolePermission得到Permission的集合呢？
        //因为你要确定这个角色是否已经被删除，如果已经被删除此角色的roleId就要为空
        return sysRoleDao.listByIds(roleIdList);
    }
}
