package com.hrms.api.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/4/23 2:03
 */
@Data
public class RegisterNewEmployeeVO implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 员工编码
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 学历
     */
    private String degree;
    /**
     * 基本工资
     */
    private Integer baseSalary;
    /**
     * 绩效工资
     */
    private Integer performanceSalary;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 岗位名称
     */
    private String jobName;
    /**
     * 审核状态
     */
    private String approvalStatus;
    /**
     * 审批人
     */
    private String approvalUser;
    /**
     * 审批意见
     */
    private String approvalComments;
    /**
     * 修改时间，用于排序
     */
    private LocalDateTime updateTime;
}
