package com.hrms.api.domain.condition;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/5/2 21:35
 */
@Data
public class SignCondition implements Serializable {
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
     * 状态  0是正常 1是异常
     */
    private Boolean status;
    /**
     * 说明
     */
    private String description;
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
    /**
     * 年
     */
    private Integer year;
    /**
     * 月
     */
    private Integer month;
    /**
     * 日
     */
    private Integer day;
}
