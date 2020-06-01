package com.hrms.api.service;

import com.hrms.api.domain.entity.OnboardingLeavingTrend;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/6/2 0:46
 */
public interface OnboardingLeavingTrendService {
    /**
     * 添加新的月份的入职离职人数统计
     *
     * @param onboardingLeavingTrend
     * @return
     * @throws DaoException
     */
    public Long insert(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException;

    /**
     * 获得最新的1年12个月的入职离职记录
     *
     * @return
     * @throws DaoException
     */
    public List<OnboardingLeavingTrend> listNewOneYear() throws DaoException;
}
