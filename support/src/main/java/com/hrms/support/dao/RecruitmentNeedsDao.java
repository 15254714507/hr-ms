package com.hrms.support.dao;

import com.hrms.api.domain.condition.RecruitmentNeedsCondition;
import com.hrms.api.domain.entity.RecruitmentNeeds;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/10 16:05
 */
@Mapper
public interface RecruitmentNeedsDao {
    /**
     * 根据id获得招聘需求
     *
     * @param id
     * @return
     */
    public RecruitmentNeeds getById(Long id);

    /**
     * 添加新的招聘需求
     *
     * @param recruitmentNeeds
     * @return
     */
    public Long insert(RecruitmentNeeds recruitmentNeeds);

    /**
     * 修改招聘需求
     *
     * @param recruitmentNeeds
     * @return
     */
    public Long updateById(RecruitmentNeeds recruitmentNeeds);

    /**
     * 删除招聘需求
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得招聘需求的集合
     *
     * @param recruitmentNeedsCondition
     * @return
     */
    public List<RecruitmentNeeds> list(RecruitmentNeedsCondition recruitmentNeedsCondition);
}
