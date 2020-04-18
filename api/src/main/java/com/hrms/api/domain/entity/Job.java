package com.hrms.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 岗位信息
 *
 * @author 孔超
 * @date 2020/4/13 22:00
 */
@Data
public class Job implements Serializable {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 部门id
     */
    private Long departmentId;
    /**
     * 岗位名称
     */
    private String jobName;
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
