package com.hrms.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/5/6 1:04
 */
@Data
public class Wages implements Serializable {
    /**
     * id
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
     * 基础工资
     */
    private Double baseSalary;
    /**
     * 绩效工资
     */
    private Double performanceSalary;
    /**
     * 养老保险
     */
    private Double pensionInsurance;
    /**
     * 医疗保险
     */
    private Double medicalInsurance;
    /**
     * 失业保险
     */
    private Double unemploymentInsurance;
    /**
     * 工伤保险
     */
    private Double injuryInsurance;
    /**
     * 生育保险
     */
    private Double fertilityInsurance;
    /**
     * 住房公积金
     */
    private Double housingProvidentFund;
    /**
     * 个人所得税
     */
    private Double personalIncomeTax;
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
     * 实际所得
     */
    private Double paidWages;
    /**
     * 此工资是哪年哪月的工资
     */
    private LocalDate wagesDate;
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
