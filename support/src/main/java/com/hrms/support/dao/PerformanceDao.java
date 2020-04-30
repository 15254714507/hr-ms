package com.hrms.support.dao;

import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.entity.Performance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/30 1:53
 */
@Mapper
public interface PerformanceDao {
    /**
     * 根据id获得绩效信息
     *
     * @param id
     * @return
     */
    public Performance getById(Long id);

    /**
     * 添加新的绩效信息
     *
     * @param performance
     * @return
     */
    public Long insert(Performance performance);

    /**
     * 修改绩效信息
     *
     * @param performance
     * @return
     */
    public Long updateById(Performance performance);

    /**
     * 删除绩效信息
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得绩效信息的集合
     *
     * @param performanceCondition
     * @return
     */
    public List<Performance> list(PerformanceCondition performanceCondition);
}
