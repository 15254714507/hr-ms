package com.hrms.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/5/3 22:22
 */
@Data
public class RequestForLeave implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 假期开始时间
     */
    private LocalDate startDate;
    /**
     * 假期结束时间
     */
    private LocalDate endDate;
    /**
     * 调休
     */
    private Double daysOfRecess;
    /**
     * 事假
     */
    private Double daysOfLeave;
    /**
     * 病假
     */
    private Double daysOfSickLeave;
    /**
     * 旷工
     */
    private Double daysOfAbsenteeism;
    /**
     * 说明
     */
    private String description;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 申请状态 0是不生效 1是生效
     */
    private Boolean status;
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
