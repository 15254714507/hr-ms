package com.hrms.webapp.sumbit;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 修改考勤异常后提交的表单
 *
 * @author 孔超
 * @date 2020/5/5 21:20
 */
@Data
public class AttendanceExceptionForm {
    /**
     * id
     */
    @NotNull
    private Long id;
    /**
     * 上班时间
     */
    @NotNull
    private String workTime;
    /**
     * 下班时间
     */
    @NotNull
    private String getOffWork;
    /**
     * 原因
     */
    @NotNull
    private String reason;
}
