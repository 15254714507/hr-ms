package com.sso.domain.entity;

import lombok.Data;

/**
 * 角色和权限关系对应
 *
 * @author 孔超
 * @date 2020/4/10 16:58
 */
@Data
public class SysRolePermission {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限的id
     */
    private Long permissionId;
}
