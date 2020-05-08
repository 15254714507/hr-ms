package com.hrms.webapp.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 核对工资信息提交的表单
 *
 * @author 孔超
 * @date 2020/5/8 1:21
 */
@Data
public class CheckWagesForm {
    /**
     * id
     */
    @NotNull
    private Long id;
    /**
     * 基础工资
     */
    private Double baseSalary;
    /**
     * 绩效工资
     */
    private Double performanceSalary;
    /**
     * 调休
     */
    @NotNull
    private Double daysOfRecess;
    /**
     * 事假
     */
    @NotNull
    private Double daysOfLeave;
    /**
     * 病假
     */
    @NotNull
    private Double daysOfSickLeave;
    /**
     * 旷工
     */
    @NotNull
    private Double daysOfAbsenteeism;
}
