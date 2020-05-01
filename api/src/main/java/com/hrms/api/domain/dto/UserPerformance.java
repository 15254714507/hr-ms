package com.hrms.api.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 孔超
 * @date 2020/4/30 22:02
 */
@Data
public class UserPerformance implements Serializable {
    /**
     * user的id
     */
    private Long userId;
    /**
     * 绩效表的id
     */
    private Long performanceId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门
     */
    private String departmentName;
    /**
     * 岗位
     */
    private String jobName;
    /**
     * 考核项
     */
    private String goal;
    /**
     * kpi
     */
    private Double kpi;
    /**
     * 绩效的状态
     */
    private Boolean status;
    /**
     * 绩效的日期
     */
    private String yearMonth;
    /**
     * 确认kpi的人
     */
    private String auditUser;
}
