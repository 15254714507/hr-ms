package com.hrms.api.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 入职离职趋势对象
 *
 * @author 孔超
 * @date 2020/6/1 23:13
 */
@Data
public class OnboardingLeavingTrend {
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
