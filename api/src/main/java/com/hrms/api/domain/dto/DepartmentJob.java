package com.hrms.api.domain.dto;

import com.hrms.api.domain.entity.Job;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 部门岗位
 *
 * @author 孔超
 * @date 2020/4/19 20:25
 */
@Data
public class DepartmentJob implements Serializable {
    /**
     * 部门的id
     */
    private Long departmentId;
    /**
     * 部门的名称
     */
    private String departmentName;
    /**
     * 岗位的名称
     */
    private List<Job> jobList;
}
