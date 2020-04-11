package com.sso.domain.entity;

import lombok.Data;

/**
 * 用户和角色对应表
 *
 * @author 孔超
 * @date 2020/4/10 16:54
 */
@Data
public class SysUserRole {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;
}
