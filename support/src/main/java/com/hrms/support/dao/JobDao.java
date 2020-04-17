package com.hrms.support.dao;

import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.entity.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:36
 */
@Mapper
public interface JobDao {
    /**
     * 根据id获得岗位信息
     *
     * @param id
     * @return
     */
    public Job getById(Long id);

    /**
     * 添加新的岗位
     *
     * @param job
     * @return
     */
    public Long insert(Job job);

    /**
     * 修改岗位信息
     *
     * @param job
     * @return
     */
    public Long updateById(Job job);

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
     * @param jobCondition
     * @return
     */
    public List<Job> list(JobCondition jobCondition);
}
