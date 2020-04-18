package com.hrms.webapp.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(max = 16, min = 6)
    private String username;
    /**
     * 密码
     */
    @NotNull
    @Size(max = 16, min = 6)
    private String password;
}
