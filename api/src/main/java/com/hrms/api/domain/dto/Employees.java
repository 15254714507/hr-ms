package com.hrms.api.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 孔超
 * @date 2020/4/17 23:21
 */
@Data
public class Employees implements Serializable {
    /**
     * user的id
     */
    private Long userId;
    /**
     * 名字
     */
    private String name;
    /**
     * 头像地址
     */
    private String headShot;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 岗位名称
     */
    private String jobName;
    /**
     * 此岗位是否是当前部门的领导
     */
    private Boolean lead;

}
