package com.hrms.support.dao;

import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.entity.UserJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:35
 */
@Mapper
public interface UserJobDao {
    /**
     * 根据id获得用户和岗位的对应关系
     *
     * @param id
     * @return
     */
    public UserJob getById(Long id);

    /**
     * 添加新用户和岗位的对应关系
     *
     * @param userJob
     * @return
     */
    public Long insert(UserJob userJob);

    /**
     * 修改岗位信息
     *
     * @param userJob
     * @return
     */
    public Long updateById(UserJob userJob);

    /**
     * 删除岗位信息
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得岗位信息的集合
     *
     * @param userJobCondition
     * @return
     */
    public List<UserJob> list(UserJobCondition userJobCondition);
}
