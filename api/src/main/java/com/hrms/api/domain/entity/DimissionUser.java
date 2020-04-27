package com.hrms.api.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/4/26 19:15
 */
@Data
public class DimissionUser implements Serializable {
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
     * 部门名称
     */
    private String departmentName;
    /**
     * 岗位名称
     */
    private String jobName;
    /**
     * 入职时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfEntry;
    /**
     * 离职时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfSeparation;
    /**
     * 离职原因
     */
    private String reasonsForSeparation;
    /**
     * 离职进行的步骤 0是提交申请 1待审核 2审核完成 3打印离职证明 4完成
     */
    private Integer steps;
    /**
     * 审核人
     */
    private String approvalUser;
    /**
     * 审核意见 ，默认为同意
     */
    private String approvalComments;
    /**
     * 实习还是全职
     */
    private String typesOfEmployees;
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
