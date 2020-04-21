package com.hrms.api.service;

import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.until.Result;

/**
 * @author 孔超
 * @date 2020/4/21 19:25
 */
public interface RegisterNewEmployeeService {
    /**
     * 添加待审核新员工
     *
     * @param registerNewEmployee 新员工的所有信息
     * @return 返回执行后的信息
     */
    public Result insert(RegisterNewEmployee registerNewEmployee);
}
