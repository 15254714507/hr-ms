package com.hrms.api.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 孔超
 * @date 2020/5/12 1:59
 */
@Data
public class Recruiters {
    /**
     * id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 应聘岗位
     */
    private String positions;
    /**
     * 类型  实习/全职
     */
    private String type;
    /**
     * 简历id
     */
    private String resumeId;
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
