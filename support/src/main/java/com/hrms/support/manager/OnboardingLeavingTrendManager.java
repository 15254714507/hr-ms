package com.hrms.support.manager;

import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.condition.OnboardingLeavingTrendCondition;
import com.hrms.api.domain.entity.Job;
import com.hrms.api.domain.entity.OnboardingLeavingTrend;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/6/1 23:40
 */
public interface OnboardingLeavingTrendManager {
    /**
     * 根据id获得岗位信息
     *
     * @param id
     * @return 返回岗位对象
     * @throws DaoException
     */
    public OnboardingLeavingTrend getById(Long id) throws DaoException;

    /**
     * 添加新的岗位
     *
     * @param onboardingLeavingTrend
     * @return
     * @throws DaoException
     */
    public Long insert(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException;

    /**
     * 修改岗位信息
     *
     * @param onboardingLeavingTrend
     * @return
     * @throws DaoException
     */
    public Long updateById(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException;

    /**
     * 删除岗位信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得岗位信息的集合
     *
     * @param onboardingLeavingTrendCondition
     * @return
     * @throws DaoException
     */
    public List<OnboardingLeavingTrend> list(OnboardingLeavingTrendCondition onboardingLeavingTrendCondition) throws DaoException;
}
