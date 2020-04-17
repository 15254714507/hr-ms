package com.hrms.api.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 岗位和人员对应
 *
 * @author 孔超
 * @date 2020/4/13 22:57
 */
@Data
public class UserJob {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 职工信息表的id
     */
    private Long userId;
    /**
     * 岗位的id
     */
    private Long jobId;
    /**
     * 员工类型，分全职还是实习
     */
    private String typesOfEmployees;
    /**
     * 基本工资
     */
    private Integer baseSalary;
    /**
     * 绩效工资
     */
    private Integer performanceSalary;
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
