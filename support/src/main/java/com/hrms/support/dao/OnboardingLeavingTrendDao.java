package com.hrms.support.dao;

import com.hrms.api.domain.condition.OnboardingLeavingTrendCondition;
import com.hrms.api.domain.entity.OnboardingLeavingTrend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/6/1 23:18
 */
@Mapper
public interface OnboardingLeavingTrendDao {
    /**
     * 根据id获得当月的离职入职数量
     *
     * @param id
     * @return
     */
    public OnboardingLeavingTrend getById(Long id);

    /**
     * 添加新的月的离职和入职人数
     *
     * @param onboardingLeavingTrend
     * @return
     */
    public Long insert(OnboardingLeavingTrend onboardingLeavingTrend);

    /**
     * 修改入职和离职人数
     *
     * @param onboardingLeavingTrend
     * @return
     */
    public Long updateById(OnboardingLeavingTrend onboardingLeavingTrend);

    /**
     * 删除此条入职离职数量的记录
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得离职入职的数量的集合
     *
     * @param onboardingLeavingTrendCondition
     * @return
     */
    public List<OnboardingLeavingTrend> list(OnboardingLeavingTrendCondition onboardingLeavingTrendCondition);
}
