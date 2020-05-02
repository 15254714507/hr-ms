package com.hrms.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/5/2 21:28
 */
@Data
public class Sign implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 上班时间
     */
    private LocalDateTime workTime;
    /**
     * 下班时间
     */
    private LocalDateTime getOffWork;
    /**
     * 加班时间
     */
    private Double workOvertime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 修改者
     */
    private String updateUser;
    /**
     * 是否删除
     */
    private Boolean delete;
}
