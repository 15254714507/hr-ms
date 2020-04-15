package com.hrms.api.domain.condition;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@Data
public class UserCondition implements Serializable {
    /**
     * 主键 id
     */
    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String identityCard;
    /**
     * 性别 0是男 1是女
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private LocalDate dateOfBirth;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 民族
     */
    private String national;
    /**
     * 学历
     */
    private String degree;
    /**
     * 专业
     */
    private String professional;
    /**
     * 大学
     */
    private String university;
    /**
     * 毕业时间
     */
    private LocalDate graduationDate;
    /**
     * 参加工作时间(第一份工作时间)
     */
    private LocalDate firstWorkDate;
    /**
     * 工作年限
     */
    private double workYears;
    /**
     * 户口所在地
     */
    private String censusRegister;
    /**
     * 住址
     */
    private String address;
    /**
     * 头像地址
     */
    private String headShot;
    /**
     * 简历地址
     */
    private String resume;
    /**
     * 入职时间
     */
    private LocalDate employmentDate;
    /**
     * 实习时间
     */
    private LocalDate internshipDate;

    /**
     * 创建时间
     */
    private LocalTime createTime;
    /**
     * 修改时间
     */
    private LocalTime updateTime;
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
