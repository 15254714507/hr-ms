package com.hrms.api.domain.condition;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/6/1 23:17
 */
@Data
public class OnboardingLeavingTrendCondition implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 入职人数
     */
    private Integer numberOfSeparations;
    /**
     * 离职人数
     */
    private Integer numberOfEmployees;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 记录数
     */
    private Integer rows;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
}
