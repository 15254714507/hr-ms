package com.hrms.webapp.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 孔超
 * @date 2020/4/12 16:06
 */
@Data
public class LoginForm {
    /**
     * 账号
     */
    @NotNull
    private String username;
    /**
     * 密码
     */
    @NotNull
    private String password;
}
