package com.hrms.webapp.sumbit;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author 孔超
 * @date 2020/5/4 20:55
 */
@Data
public class RequestVacationForm {
    /**
     * 账号
     */
    @NotNull
    private String username;
    /**
     * 姓名
     */
    @NotNull
    private String name;
    /**
     * 请假的类型 0是调休 1是事假 2是病假
     */
    @NotNull
    private Integer type;
    /**
     * 开始时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    /**
     * 结束时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    /**
     * 请假天数
     */
    @NotNull
    private Integer days;
    /**
     * 说明
     */
    @NotNull
    private String description;
}
