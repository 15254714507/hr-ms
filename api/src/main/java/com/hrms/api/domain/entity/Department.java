package com.hrms.api.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 部门信息
 *
 * @author 孔超
 * @date 2020/4/13 21:57
 */
@Data
public class Department {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 部门名称
     */
    private String departmentName;
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
