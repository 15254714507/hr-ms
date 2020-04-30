package com.hrms.api.domain.condition;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/4/30 1:53
 */
@Data
public class PerformanceCondition implements Serializable {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 绩效目标
     */
    private String goal;
    /**
     * kpi： 0.8 、1.0 、1.2
     */
    private Double kpi;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 绩效的状态 false是当月绩效不生效(月初)  true是当月绩效生效(月底)
     */
    private Boolean status;
    /**
     * 绩效的年
     */
    private Integer year;
    /**
     * 绩效的月
     */
    private Integer month;
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
}
