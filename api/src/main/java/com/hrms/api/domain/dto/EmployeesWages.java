package com.hrms.api.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 孔超
 * @date 2020/5/9 23:10
 */
@Data
public class EmployeesWages implements Serializable {
    /**
     * 职工信息
     */
    private Employees employees;
    /**
     * 用户和岗位对应表的id
     */
    private Long userJobId;
    /**
     * 基础工资
     */
    private Integer baseSalary;
    /**
     * 绩效工资
     */
    private Integer performanceSalary;
}
