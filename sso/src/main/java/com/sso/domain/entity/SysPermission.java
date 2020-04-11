package com.sso.domain.entity;

import lombok.Data;

import java.time.LocalTime;

/**
 * 权限信息
 *
 * @author 孔超
 * @date 2020/4/9 16:56
 */
@Data
public class SysPermission {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 资源类型
     */
    private Integer resourceType;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源标识
     */
    private String resourceCode;
    /**
     * uri路径
     */
    private String uri;
    /**
     * 序号
     */
    private Integer seq;
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
