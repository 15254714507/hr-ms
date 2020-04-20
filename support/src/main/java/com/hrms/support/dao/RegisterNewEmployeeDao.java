package com.hrms.support.dao;

import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 入职但是还没审核通过
 *
 * @author 孔超
 * @date 2020/4/20 16:43
 */
@Mapper
public interface RegisterNewEmployeeDao {
    /**
     * 根据id获取入职但是还没审核通过的信息
     *
     * @param id 用户id
     * @return 返回用户对象
     */
    public RegisterNewEmployee getById(Long id);

    /**
     * 添加入职但是还没审核通过的员工
     *
     * @param registerNewEmployee 要添加入职但是还没审核对象
     * @return 返回是否成功 1是成功，否则其他的都会报错
     */
    public Long insert(RegisterNewEmployee registerNewEmployee);

    /**
     * 修改入职但是还没审核通过的员工的信息
     *
     * @param registerNewEmployee
     * @return
     */
    public Long updateById(RegisterNewEmployee registerNewEmployee);

    /**
     * 删除此员工
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得入职还没通过的员工的集合
     *
     * @param registerNewEmployeeCondition
     * @return
     */
    public List<RegisterNewEmployee> list(RegisterNewEmployeeCondition registerNewEmployeeCondition);
}
