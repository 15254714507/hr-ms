package com.sso.domain.entity;

import lombok.Data;

import java.time.LocalTime;

/**
 * 角色信息
 *
 * @author 孔超
 * @date 2020/4/10 16:50
 */
@Data
public class SysRole {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色描述
     */
    private String roleDescription;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 修改者
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private LocalTime createTime;
    /**
     * 修改时间
     */
    private LocalTime updateTime;
    /**
     * 是否删除
     */
    private Boolean delete;
}
