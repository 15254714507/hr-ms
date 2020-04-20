package com.hrms.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/4/20 16:23
 */
@Data
public class RegisterNewEmployee implements Serializable {
    /**
     * 主键 id
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
     * 身份证号
     */
    private String identityCard;
    /**
     * 性别 0是男 1是女
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private LocalDate dateOfBirth;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 民族
     */
    private String national;
    /**
     * 学历
     */
    private String degree;
    /**
     * 专业
     */
    private String professional;
    /**
     * 大学
     */
    private String university;
    /**
     * 毕业时间
     */
    private LocalDate graduationDate;
    /**
     * 参加工作时间(第一份工作时间)
     */
    private LocalDate firstWorkDate;
    /**
     * 工作年限
     */
    private double workYears;
    /**
     * 户口所在地
     */
    private String censusRegister;
    /**
     * 住址
     */
    private String address;
    /**
     * 部门的id
     */
    private Long departmentId;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 岗位id
     */
    private Long jobId;
    /**
     * 岗位名称
     */
    private String jobName;
    /**
     * 入职时间
     */
    private LocalDate employmentDate;
    /**
     * 实习时间
     */
    private LocalDate internshipDate;
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
     * 审批人
     */
    private String approvalUser;
    /**
     * 审批状态
     */
    private Integer approvalStatus;
    /**
     * 审批意见
     */
    private String approvalComments;
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
