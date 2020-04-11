package com.sso.domain.entity;

import lombok.Data;

import java.time.LocalTime;

/**
 * 用户信息
 *
 * @author 孔超
 * @date 2020/4/10 15:09
 */
@Data
public class SysUser {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
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
