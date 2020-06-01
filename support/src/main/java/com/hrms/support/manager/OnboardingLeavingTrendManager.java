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
     * 根据id获得某个月的离职入职人数统计
     *
     * @param id
     * @return 返回岗位对象
     * @throws DaoException
     */
    public OnboardingLeavingTrend getById(Long id) throws DaoException;

    /**
     * 添加新的月份的入职离职人数的统计
     *
     * @param onboardingLeavingTrend
     * @return
     * @throws DaoException
     */
    public Long insert(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException;

    /**
     * 修改某个月份的入职离职人数的信息
     *
     * @param onboardingLeavingTrend
     * @return
     * @throws DaoException
     */
    public Long updateById(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException;

    /**
     * 删除某个月份的入职离职人数的信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得入职离职人数的统计信息的集合
     *
     * @param onboardingLeavingTrendCondition
     * @return
     * @throws DaoException
     */
    public List<OnboardingLeavingTrend> list(OnboardingLeavingTrendCondition onboardingLeavingTrendCondition) throws DaoException;
}
