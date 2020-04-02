package com.hrms.api.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@Data
public class User implements Serializable {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
