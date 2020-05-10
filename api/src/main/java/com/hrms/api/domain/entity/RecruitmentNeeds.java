package com.hrms.api.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/5/10 15:59
 */
@Data
public class RecruitmentNeeds {
    /**
     * id
     */
    private Long id;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 岗位名称
     */
    private String jobName;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 员工类型 实习/全职
     */
    private String typeOfEmployees;
    /**
     * 岗位职责
     */
    private String jobResponsibilities;
    /**
     * 岗位要求
     */
    private String jobRequirements;
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
