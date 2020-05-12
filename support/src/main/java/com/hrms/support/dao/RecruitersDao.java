package com.hrms.support.dao;

import com.hrms.api.domain.condition.RecruitersCondition;
import com.hrms.api.domain.entity.Recruiters;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/12 2:03
 */
@Mapper
public interface RecruitersDao {
    /**
     * 根据id获得应聘者信息
     *
     * @param id
     * @return
     */
    public Recruiters getById(Long id);

    /**
     * 添加新的应聘者信息
     *
     * @param recruiters
     * @return
     */
    public Long insert(Recruiters recruiters);

    /**
     * 修改应聘者信息
     *
     * @param recruiters
     * @return
     */
    public Long updateById(Recruiters recruiters);

    /**
     * 删除应聘者信息
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得应聘者的集合
     *
     * @param recruitersCondition
     * @return
     */
    public List<Recruiters> list(RecruitersCondition recruitersCondition);
}
